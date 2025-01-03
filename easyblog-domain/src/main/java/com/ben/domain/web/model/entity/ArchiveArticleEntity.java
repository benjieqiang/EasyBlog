package com.ben.domain.web.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-14  20:41
 * @Description: 文章归类
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArchiveArticleEntity {
    private Long id;
    private String cover;
    private String title;
    /**
     * 发布日期
     */
    private LocalDate createDate;

    /**
     * 发布的月份
     */
    private YearMonth createMonth;
}
