package com.ben.infrastructure.persistent.search.runner;

import com.ben.infrastructure.persistent.dao.IArticleContentDao;
import com.ben.infrastructure.persistent.dao.IArticleDao;
import com.ben.infrastructure.persistent.po.Article;
import com.ben.infrastructure.persistent.po.ArticleContent;
import com.ben.infrastructure.persistent.search.LuceneHelper;
import com.ben.infrastructure.persistent.search.index.ArticleIndex;
import com.ben.types.common.DateTimeConstants;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/*
 * @description 索引接口
 * @author benjieqiang
 * @date 2025/1/4 4:03 PM
 */
@Component
@Slf4j
public class InitLuceneIndexRunner implements CommandLineRunner {

    @Value("${lucene.indexDir}")
    private String indexDir;

    @Autowired
    private LuceneHelper luceneHelper;
    @Autowired
    private IArticleDao articleDao;
    @Autowired
    private IArticleContentDao articleContentDao;

    @Override
    public void run(String... args) throws Exception {
        log.info("==> 开始初始化 Lucene 索引...");

        // 查询所有文章
        List<Article> articles = articleDao.selectPageList(null, null, null, null);

        // 未发布文章，则不创建 lucene 索引
        if (articles.isEmpty()) {
            log.info("==> 未发布任何文章，暂不创建 Lucene 索引...");
            return;
        }

        // 若配置文件中未配置索引存放目录，日志提示错误信息
        if (StringUtils.isBlank(indexDir)) {
            log.error("==> 未指定 Lucene 索引存放位置，需在 application.yml 文件中添加路径配置...");
            return;
        }

        List<Document> documents = Lists.newArrayList();
        articles.forEach(article -> {
            Long articleId = article.getId();

            // 查询文章正文
            ArticleContent articleContent = articleContentDao.selectByArticleId(articleId);
            // 构建文档
            Document document = new Document();
            // 设置文档字段 Field
            document.add(new TextField(ArticleIndex.COLUMN_ID, String.valueOf(articleId), Field.Store.YES));
            document.add(new TextField(ArticleIndex.COLUMN_TITLE, article.getTitle(), Field.Store.YES));
            document.add(new TextField(ArticleIndex.COLUMN_COVER, article.getCover(), Field.Store.YES));
            document.add(new TextField(ArticleIndex.COLUMN_SUMMARY, article.getSummary(), Field.Store.YES));
            document.add(new TextField(ArticleIndex.COLUMN_CONTENT, articleContent.getContent(), Field.Store.YES));
            document.add(new TextField(ArticleIndex.COLUMN_CREATE_TIME, DateTimeConstants.DATE_TIME_FORMATTER.format(article.getCreateTime()), Field.Store.YES));
            documents.add(document);
        });

        // 创建索引
        luceneHelper.createIndex(ArticleIndex.NAME, documents);

        log.info("==> 结束初始化 Lucene 索引...");
    }
}