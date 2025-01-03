package com.ben.domain.web.service.impl;

import com.ben.domain.web.model.entity.TagEntity;
import com.ben.domain.web.repository.ITagRepository;
import com.ben.domain.web.service.IWebTagService;
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
public class WebTagServiceImpl implements IWebTagService {
    @Autowired
    private ITagRepository tagRepository;

    @Override
    public List<TagEntity> findTagSelectList(Long size) {
        return tagRepository.findTagSelectList(size);
    }
}
