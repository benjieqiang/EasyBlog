package com.ben.domain.admin.model.aggregate;

import com.ben.domain.admin.model.entity.WikiEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-05  11:57
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WikiPageAggregate {
    private Long total;
    private Integer current;
    private Integer size;
    List<WikiEntity> wikiEntities;
}
