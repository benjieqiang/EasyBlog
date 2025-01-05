package com.ben.domain.web.model.aggregate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-05  16:27
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebWikiCatalogAggregate {
    /**
     * 知识库 ID
     */
    private Long id;

    private Long articleId;

    private String title;

    private Integer sort;

    private Integer level;

    /**
     * 二级目录
     */
    private List<WebWikiCatalogAggregate> children;

}
