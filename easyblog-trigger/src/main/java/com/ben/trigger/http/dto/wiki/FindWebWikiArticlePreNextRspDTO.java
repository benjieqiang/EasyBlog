package com.ben.trigger.http.dto.wiki;

import com.ben.trigger.http.dto.article.FindIndexPreNextArticleRspDTO;
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
public class FindWebWikiArticlePreNextRspDTO {
    private FindIndexPreNextArticleRspDTO preArticle;
    private FindIndexPreNextArticleRspDTO nextArticle;
}
