package com.ben.domain.web.service;

import com.ben.domain.web.model.entity.TagEntity;

import java.util.List;

/**
 * @Description: IWebTagService
 * @Author: benjieqiang
 * @LastChangeDate: 2024/12/13 10:49 AM
 * @Version: v1.0
 */

public interface IWebTagService {
    List<TagEntity> findTagSelectList();
}

