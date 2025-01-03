package com.ben.trigger.http.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author benjieqiang
 * @description 前端 分类文章分页响应rsp
 * @date 2024/12/12 3:32 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindWebCategoryArticlePageListRspDTO {
    private Long id;
    private String cover;
    private String title;
    private LocalDate createDate;
}
