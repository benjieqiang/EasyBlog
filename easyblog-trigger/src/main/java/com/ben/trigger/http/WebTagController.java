package com.ben.trigger.http;

import com.ben.domain.web.model.entity.TagEntity;
import com.ben.domain.web.service.IWebTagService;
import com.ben.trigger.http.dto.common.SelectRspDTO;
import com.ben.types.annotations.ApiOperationLog;
import com.ben.types.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  10:49
 * @Description: Admin 标签模块
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/${app.config.api-version}/tag")
@Api(tags = "Web 标签模块")
public class WebTagController {

    @Autowired
    private IWebTagService tagService;


    @GetMapping ("/select/list")
    @ApiOperation(value = "查询所有标签数据")
    @ApiOperationLog(description = "查询所有标签数据")
    public Response findTagSelectList() {
        List<TagEntity> tagSelectList = tagService.findTagSelectList();
        List<SelectRspDTO> selectRspDTOS = null;
        if (!CollectionUtils.isEmpty(tagSelectList)) {
            selectRspDTOS = tagSelectList.stream()
                    .map(tag -> SelectRspDTO.builder()
                            .label(tag.getName())
                            .value(String.valueOf(tag.getId()))
                            .build())
                    .collect(Collectors.toList());
        }
        return Response.success(selectRspDTOS);
    }

}