package com.ben.infrastructure.persistent.search.index;

/*
 * @description 索引接口
 * @author benjieqiang
 * @date 2025/1/4 4:03 PM
 */
public interface ArticleIndex {
    /**
     * 索引名称
     */
    String NAME = "article";

    // --------------------- 文档字段 ---------------------
    String COLUMN_ID = "id";

    String COLUMN_TITLE = "title";

    String COLUMN_COVER = "cover";

    String COLUMN_SUMMARY = "summary";

    String COLUMN_CONTENT = "content";

    String COLUMN_CREATE_TIME = "createTime";
}
