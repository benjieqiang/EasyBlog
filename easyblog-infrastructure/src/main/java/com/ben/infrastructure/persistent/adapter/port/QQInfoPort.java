package com.ben.infrastructure.persistent.adapter.port;

import com.alibaba.fastjson2.JSON;
import com.ben.domain.web.adapter.port.IQQInfoPort;
import com.ben.domain.web.model.entity.QQUserInfoEntity;
import com.ben.infrastructure.persistent.gateway.IQQInfoService;
import com.ben.infrastructure.persistent.gateway.dto.QQUserInfoRspDTO;
import com.ben.types.enums.ResponseCode;
import com.ben.types.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.Map;
import java.util.Objects;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-06  11:12
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@Service
public class QQInfoPort implements IQQInfoPort {

    @Autowired
    private IQQInfoService qqInfoService;

    @Override
    public QQUserInfoEntity findQQUserInfo(String qq) {
        try {
            // 校验 QQ 号
            String PURE_NUMBER_REGEX = "\\d+";
            if (!qq.matches(PURE_NUMBER_REGEX)) {
                log.warn("昵称输入的格式不是 QQ 号: {}", qq);
                throw new BizException(ResponseCode.NOT_QQ_NUMBER);
            }

            Call<QQUserInfoRspDTO> qqInfo = qqInfoService.findQQInfo(qq);
            log.error("请求第三方QQ接口，qq:{} qqInfo:{}", qq, qqInfo);

            Response<QQUserInfoRspDTO> response;

            try {
                response = qqInfo.execute();
            } catch (Exception e) {
                log.error("请求第三方QQ接口异常，qq:{} error:{}", qq, e.getMessage(), e);
                throw new BizException(ResponseCode.GATEWAY_ERROR);
            }

            log.info("请求第三方QQ接口完成 qq:{} response:{}", qq, JSON.toJSONString(response));

            // 检查 HTTP 响应是否成功
            if (!response.isSuccessful() || response.body() == null) {
                log.error("请求失败，HTTP状态码：{}，响应体：{}", response.code(), JSON.toJSONString(response.errorBody()));
                throw new BizException(ResponseCode.GATEWAY_ERROR);
            }

            // 解析响应体
            QQUserInfoRspDTO userInfoRspDTO = response.body();
            log.info("通过 QQ 号获取用户信息: {}", JSON.toJSONString(userInfoRspDTO));

            // 构建 QQUserInfoEntity 对象并返回
            return QQUserInfoEntity.builder()
                    .avatar(userInfoRspDTO.getImgurl())
                    .nickname(userInfoRspDTO.getName())
                    .mail(userInfoRspDTO.getMail())
                    .build();

        } catch (Exception e) {
            log.error("请求第三方QQ接口失败 qq:{} ", qq, e);
            throw e;
        }
    }
}
