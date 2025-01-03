package com.ben.trigger.http.dto.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author benjieqiang
 * @description 前端 标签文章分页响应rsp
 * @date 2024/12/12 3:32 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindWebTagArticlePageListRspDTO {
    private Long id;
    private String cover;
    private String title;
    private LocalDate createDate;
}
