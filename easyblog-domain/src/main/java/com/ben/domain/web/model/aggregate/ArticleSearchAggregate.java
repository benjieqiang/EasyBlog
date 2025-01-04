package com.ben.domain.web.model.aggregate;

import com.ben.domain.web.model.entity.ArticleSearchEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-04  16:50
 * @Description: 文章搜索聚合
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleSearchAggregate {
    private Long total;
    private Integer current;
    private Integer size;
    private List<ArticleSearchEntity> articleEntityList;
}
