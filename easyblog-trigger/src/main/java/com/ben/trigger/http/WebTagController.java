package com.ben.trigger.http;

import com.ben.domain.web.model.entity.TagEntity;
import com.ben.domain.web.service.IWebTagService;
import com.ben.trigger.http.dto.common.SelectRspDTO;
import com.ben.trigger.http.dto.tag.FindWebTagReqDTO;
import com.ben.trigger.http.dto.tag.FindWebTagRspDTO;
import com.ben.types.annotations.ApiOperationLog;
import com.ben.types.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  10:49
 * @Description: 前台 标签模块
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/${app.config.api-version}/tag")
@Api(tags = "前台 标签模块")
public class WebTagController {

    @Autowired
    private IWebTagService tagService;


    @PostMapping("/list")
    @ApiOperation(value = "查询标签数据")
    @ApiOperationLog(description = "查询标签数据")
    public Response findTagSelectList(@RequestBody @Validated FindWebTagReqDTO request) {
        Long size = request.getSize();
        List<TagEntity> tagSelectList = tagService.findTagSelectList(size);
        List<FindWebTagRspDTO> webTagRspDTOS = null;
        if (!CollectionUtils.isEmpty(tagSelectList)) {
            webTagRspDTOS = tagSelectList.stream()
                    .map(tag -> FindWebTagRspDTO.builder()
                            .id(tag.getId())
                            .name(tag.getName())
                            .articlesTotal(tag.getArticlesTotal())
                            .build())
                    .collect(Collectors.toList());
        }
        return Response.success(webTagRspDTOS);
    }

}