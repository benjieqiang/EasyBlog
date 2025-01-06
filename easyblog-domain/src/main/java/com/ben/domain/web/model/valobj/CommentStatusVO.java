package com.ben.domain.web.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-06  16:22
 * @Description: TODO
 * @Version: 1.0
 */
@AllArgsConstructor
@Getter
public enum CommentStatusVO {
    // ----------- 通用异常状态码 -----------
    WAIT_EXAMINE(1, "等待审核"),
    NORMAL(2, "正常"),
    EXAMINE_FAILED(3, "审核不通过"),
    ;

    private Integer code;
    private String description;
}
