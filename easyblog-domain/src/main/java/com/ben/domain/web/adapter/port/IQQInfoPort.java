package com.ben.domain.web.adapter.port;

import com.ben.domain.web.model.entity.QQUserInfoEntity;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-06  11:11
 * @Description: qqInfo交互接口
 * @Version: 1.0
 */
public interface IQQInfoPort {
    QQUserInfoEntity findQQUserInfo(String qq);
}
