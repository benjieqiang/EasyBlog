package com.ben.infrastructure.persistent.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-06  10:50
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// {"code":200,"imgurl":"https://q2.qlogo.cn/headimg_dl?dst_uin=12345679&spec=140","name":"Johnson.Z","mail":"12345679@qq.com"}
public class QQUserInfoRspDTO {
    private String code;
    private String msg;
    private String imgurl;
    private String name;
    private String mail;
}
