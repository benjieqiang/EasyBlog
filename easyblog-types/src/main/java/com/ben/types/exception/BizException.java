package com.ben.types.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @description 业务异常
 * @author benjieqiang
 * @date 2024/12/2 10:46 AM
 */
@Getter
@Setter
public class BizException extends RuntimeException {
    // 异常码
    private String errorCode;
    // 错误信息
    private String errorMessage;
}
