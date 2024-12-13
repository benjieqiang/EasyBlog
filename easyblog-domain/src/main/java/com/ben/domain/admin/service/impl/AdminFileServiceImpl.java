package com.ben.domain.admin.service.impl;

import com.ben.domain.admin.repository.IAdminFileRepository;
import com.ben.domain.admin.service.IAdminFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  17:04
 * @Description: 文件上传
 * @Version: 1.0
 */
@Slf4j
@Service
public class AdminFileServiceImpl implements IAdminFileService {
    @Autowired
    private IAdminFileRepository repository;
    @Override
    public String uploadFile(MultipartFile file) {
        return repository.uploadFile(file);
    }
}
