package com.ben.trigger.http.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author benjieqiang
 * @description 标签分页rsp
 * @date 2024/12/13 10:53 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindArticlePageListRspDTO {

    /**
     * 文章 ID
     */
    private Long id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章封面
     */
    private String cover;

    /**
     * 发布时间
     */
    private LocalDateTime createTime;

    /**
     * 是否置顶
     */
    private Boolean isTop;


}
