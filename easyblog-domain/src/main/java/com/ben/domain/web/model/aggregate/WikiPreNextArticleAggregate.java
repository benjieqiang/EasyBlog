package com.ben.domain.web.model.aggregate;

import com.ben.domain.web.model.entity.ArticleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-05  20:08
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WikiPreNextArticleAggregate {

    private ArticleEntity preArticle;
    private ArticleEntity nextArticle;
}
