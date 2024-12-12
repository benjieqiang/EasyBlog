package com.ben.domain.admin.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-12  15:42
 * @Description: 分页查询聚合对象
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryPageEntity {
    Integer pageNum;
    Integer pageSize;
    String name;
    LocalDateTime startDate;
    LocalDateTime endDate;
}
