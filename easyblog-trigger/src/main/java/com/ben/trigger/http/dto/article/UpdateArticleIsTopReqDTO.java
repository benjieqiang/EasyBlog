package com.ben.trigger.http.dto.article;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-30  16:54
 * @Description: 更新文章置顶状态
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "更新文章置顶状态 DTO")
public class UpdateArticleIsTopReqDTO {
    @NotNull(message = "文章 ID 不能为空")
    private Long id;

    @NotNull(message = "文章置顶状态不能为空")
    private Boolean isTop;
}
