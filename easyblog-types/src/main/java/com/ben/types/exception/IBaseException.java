package com.ben.types.exception;

/**
 * @InterfaceName: IBaseException
 * @Description: 通用异常接口
 * @Author: benjieqiang
 * @LastChangeDate: 2024/12/5 2:16 PM
 * @Version: v1.0
 */

public interface IBaseException {
    String getErrorCode();
    String getErrorMessage();
}
