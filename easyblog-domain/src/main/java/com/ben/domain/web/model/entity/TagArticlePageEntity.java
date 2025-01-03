package com.ben.domain.web.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  08:46
 * @Description: 标签实体分页对象
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagArticlePageEntity {
    private Long id;
    private String cover;
    private String title;
    private LocalDate createDate;
}
