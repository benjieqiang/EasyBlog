package com.ben.trigger.http.dto.wiki;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: benjieqiang
 * @Date: 2025-01-05  09:56
 * @Version: v1.0
 * @Description: 查询知识库目录数据出参
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindWikiCatalogListRspDTO {

    /**
     * 知识库 ID
     */
    private Long id;

    private Long articleId;

    private String title;

    private Integer sort;

    private Integer level;

    /**
     * 是否处于编辑状态（用于前端是否显示编辑输入框）
     */
    private Boolean editing;

    /**
     * 二级目录
     */
    private List<FindWikiCatalogListRspDTO> children;

}