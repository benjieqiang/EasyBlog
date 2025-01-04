package com.ben.trigger.subscriber;

import com.ben.domain.admin.event.UpdateArticleEvent;
import com.ben.infrastructure.persistent.dao.IArticleContentDao;
import com.ben.infrastructure.persistent.dao.IArticleDao;
import com.ben.infrastructure.persistent.po.Article;
import com.ben.infrastructure.persistent.po.ArticleContent;
import com.ben.infrastructure.persistent.search.LuceneHelper;
import com.ben.infrastructure.persistent.search.index.ArticleIndex;
import com.ben.types.common.DateTimeConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author: benjieqiang
 * @CreateTime:
 * @Description: 更新文章事件 监听
 * @Version: 1.0
 */
@Component
@Slf4j
public class UpdateArticleSubscriber implements ApplicationListener<UpdateArticleEvent> {


    @Autowired
    private LuceneHelper luceneHelper;
    @Autowired
    private IArticleDao articleDao;
    @Autowired
    private IArticleContentDao articleContentDao;

    @Override
    @Async("threadPoolTaskExecutor")
    public void onApplicationEvent(UpdateArticleEvent event) {
        // 在这里处理收到的事件，可以是任何逻辑操作
        Long articleId = event.getArticleId();

        // 获取当前线程名称
        String threadName = Thread.currentThread().getName();

        log.info("==> threadName: {}", threadName);
        log.info("==> 文章更新事件消费成功，articleId: {}", articleId);

        // 搜索新发布的文章
        Article article = articleDao.selectByArticleId(articleId);
        // 这里也将文字正文保存到了文档中，但是检索的时候并没有查询正文，小伙伴们可自行决定是否要将正文，添加到检索字段中
        ArticleContent articleContentDO = articleContentDao.selectByArticleId(articleId);

        // 构建文档
        Document document = new Document();
        document.add(new TextField(ArticleIndex.COLUMN_ID, String.valueOf(articleId), Field.Store.YES));
        document.add(new TextField(ArticleIndex.COLUMN_TITLE, article.getTitle(), Field.Store.YES));
        document.add(new TextField(ArticleIndex.COLUMN_COVER, article.getCover(), Field.Store.YES));
        document.add(new TextField(ArticleIndex.COLUMN_SUMMARY, article.getSummary(), Field.Store.YES));
        document.add(new TextField(ArticleIndex.COLUMN_CONTENT, articleContentDO.getContent(), Field.Store.YES));
        document.add(new TextField(ArticleIndex.COLUMN_CREATE_TIME, DateTimeConstants.DATE_TIME_FORMATTER.format(article.getCreateTime()), Field.Store.YES));

        // 更新条件（通过文章 ID 来更新）
        Term condition = new Term(ArticleIndex.COLUMN_ID, String.valueOf(articleId));

        long count = luceneHelper.updateDocument(ArticleIndex.NAME, document, condition);

        log.info("==> 更新文章对应 Lucene 文档结束，articleId: {}，受影响行数: {}", articleId, count);
    }
}