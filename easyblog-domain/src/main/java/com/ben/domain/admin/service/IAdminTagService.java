package com.ben.domain.admin.service;

import com.ben.domain.admin.model.entity.TagEntity;
import com.ben.domain.admin.model.entity.TagPageEntity;
import com.ben.types.response.Response;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @InterfaceName: IAdminTagService
 * @Description: IAdminTagService
 * @Author: benjieqiang
 * @LastChangeDate: 2024/12/13 10:49 AM
 * @Version: v1.0
 */

public interface IAdminTagService {

    void addTags(List<String> tags);

    PageInfo<TagEntity> findTagPageList(TagPageEntity tagPageEntity);

    int deleteTag(Long id);

    List<TagEntity> searchTags(String key);

    List<TagEntity> findTagSelectList();
}

