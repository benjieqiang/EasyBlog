package com.ben.trigger.http.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-07  08:52
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindCommentPageListRspDTO {
    private Long id;

    private String routerUrl;

    private String avatar;

    private String nickname;

    private String mail;

    private String website;

    private LocalDateTime createTime;

    private String content;

    private Integer status;

    private String reason;


}
