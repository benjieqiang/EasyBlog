package com.ben.domain.web.model.aggregate;

import com.ben.domain.web.model.entity.ArchiveArticleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-03  11:27
 * @Description: 查询文章归类数据
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArchiveArticleAggregate {
    /**
     * 归档的月份
     */
    private YearMonth month;

    private List<ArchiveArticleEntity> articles;
}
