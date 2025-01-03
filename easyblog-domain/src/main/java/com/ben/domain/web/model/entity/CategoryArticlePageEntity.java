package com.ben.domain.web.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-12  10:29
 * @Description: 文章分类分页实体
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryArticlePageEntity {
    private Long id;
    private String cover;
    private String title;
    private LocalDate createDate;
}
