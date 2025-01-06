package com.ben.infrastructure.persistent.gateway;

import com.ben.infrastructure.persistent.gateway.dto.QQUserInfoRspDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-06  11:19
 * @Description: TODO
 * @Version: 1.0
 */
public interface IQQInfoService {

    @GET("/api/qqinfo")
    Call<QQUserInfoRspDTO> findQQInfo(@Query("qq") String qq);
}
