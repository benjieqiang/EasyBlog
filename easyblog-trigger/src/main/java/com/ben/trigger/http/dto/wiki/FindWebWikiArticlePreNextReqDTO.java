package com.ben.trigger.http.dto.wiki;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-05  20:04
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindWebWikiArticlePreNextReqDTO {
    @NotNull(message = "知识库 ID 不能为空")
    private Long id;

    @NotNull(message = "文章 ID 不能为空")
    private Long articleId;
}
