package com.ben.infrastructure.persistent.repository;

import com.ben.domain.web.model.aggregate.ArticleSearchAggregate;
import com.ben.domain.web.model.entity.ArticleSearchEntity;
import com.ben.domain.web.repository.ISearchRepository;
import com.ben.infrastructure.persistent.search.LuceneHelper;
import com.ben.infrastructure.persistent.search.index.ArticleIndex;
import com.ben.types.response.PageResponse;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.StringReader;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-04  16:57
 * @Description: 文件搜索仓储
 * @Version: 1.0
 */
@Repository
@Slf4j
public class SearchRepository implements ISearchRepository {
    @Autowired
    private LuceneHelper luceneHelper;

    @Override
    public ArticleSearchAggregate searchArticlePageList(String word, int current, int size) {

        // 想要搜索的文档字段（这里指定对文章标题、摘要检索，任何一个字段包含该关键词，都会被搜索到）
        String[] columns = {ArticleIndex.COLUMN_TITLE, ArticleIndex.COLUMN_SUMMARY};
        // 查询总记录数
        long total = luceneHelper.searchTotal(ArticleIndex.NAME, word, columns);

        // 执行搜索（分页查询）
        List<Document> documents = luceneHelper.search(ArticleIndex.NAME, word, columns, current, size);

        // 若未查询到相关文档，直接 return
        if (CollectionUtils.isEmpty(documents)) {
            return ArticleSearchAggregate.builder()
                    .total(total)
                    .current(current)
                    .size(size)
                    .articleEntityList(null)
                    .build();
        }

        // ======================== 开始关键词高亮处理 ========================
        // 中文分析器
        Analyzer analyzer = new SmartChineseAnalyzer();
        QueryParser parser = new QueryParser(ArticleIndex.COLUMN_TITLE, analyzer);
        Query query = null;
        try {
            query = parser.parse(word);
        } catch (org.apache.lucene.queryparser.classic.ParseException e) {
            log.error("解析关键词错误:", e);
        }

        // 创建高亮器
        SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<span style=\"color: #f73131\">", "</span>");
        Highlighter highlighter = new Highlighter(formatter, new QueryScorer(query));

        List<ArticleSearchEntity> entityList = Lists.newArrayList();
        // 遍历查询到的文档，进行关键词高亮处理
        documents.forEach(document -> {
            try {
                // 文章标题
                String title = document.get(ArticleIndex.COLUMN_TITLE);

                // 获取文章标题中高亮片段
                TokenStream tokenStream = analyzer.tokenStream(ArticleIndex.COLUMN_TITLE, new StringReader(title));
                String titleFragment = highlighter.getBestFragment(tokenStream, title);

                // 如果没有匹配到关键词，则返回原始文本
                String highlightedTitle = StringUtils.isNoneBlank(titleFragment) ? titleFragment : title;

                String id = document.get(ArticleIndex.COLUMN_ID);
                String cover = document.get(ArticleIndex.COLUMN_COVER);
                String createTime = document.get(ArticleIndex.COLUMN_CREATE_TIME);
                String summary = document.get(ArticleIndex.COLUMN_SUMMARY);

                // 组装 entity
                ArticleSearchEntity entity = ArticleSearchEntity.builder()
                        .id(Long.valueOf(id))
                        .title(highlightedTitle)
                        .summary(summary)
                        .cover(cover)
                        .createDate(createTime)
                        .build();

                entityList.add(entity);
            } catch (Exception e) {
                log.error("文档转换错误: ", e);
            }
        });
        return ArticleSearchAggregate.builder()
                .total(total)
                .current(current)
                .size(size)
                .articleEntityList(entityList)
                .build();
    }
}
