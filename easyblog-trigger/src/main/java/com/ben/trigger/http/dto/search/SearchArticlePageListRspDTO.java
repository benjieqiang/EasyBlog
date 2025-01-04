package com.ben.trigger.http.dto.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchArticlePageListRspDTO {
    /**
     * 文章 ID
     */
    private Long id;
    /**
     * 封面
     */
    private String cover;
    /**
     * 标题
     */
    private String title;
    /**
     * 摘要
     */
    private String summary;
    /**
     * 发布日期
     */
    private String createDate;
}

