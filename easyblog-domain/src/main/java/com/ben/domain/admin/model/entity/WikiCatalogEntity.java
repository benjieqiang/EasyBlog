package com.ben.domain.admin.model.entity;

import com.ben.domain.admin.model.valobj.WikiCatalogLevelVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-05  11:54
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WikiCatalogEntity {
    private Long id;

    private Long wikiId;

    private Long articleId;

    private String title;

    private WikiCatalogLevelVO catalogLevelVO;

    private Long parentId;

    private Integer sort;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isDeleted;
}
