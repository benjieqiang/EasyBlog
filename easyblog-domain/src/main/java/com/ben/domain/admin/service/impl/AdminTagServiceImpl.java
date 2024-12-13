package com.ben.domain.admin.service.impl;

import com.ben.domain.admin.model.entity.TagEntity;
import com.ben.domain.admin.model.entity.TagPageEntity;
import com.ben.domain.admin.repository.IAdminTagRepository;
import com.ben.domain.admin.service.IAdminTagService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  10:50
 * @Description: TODO
 * @Version: 1.0
 */
@Service
@Slf4j
public class AdminTagServiceImpl implements IAdminTagService {
    @Autowired
    private IAdminTagRepository tagRepository;

    @Override
    public void addTags(List<String> tags) {
        tagRepository.addTags(tags);
    }

    @Override
    public PageInfo<TagEntity> findTagPageList(TagPageEntity tagPageEntity) {
        return tagRepository.findTagPageList(tagPageEntity);
    }

    @Override
    public int deleteTag(Long id) {
        return tagRepository.deleteTag(id);
    }

    @Override
    public List<TagEntity> searchTags(String key) {
        return tagRepository.selectByName(key);
    }

    @Override
    public List<TagEntity> findTagSelectList() {
        return tagRepository.findTagSelectList();
    }
}
