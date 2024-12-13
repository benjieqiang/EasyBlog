package com.ben.domain.admin.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  17:03
 * @Description: 文件上传
 * @Version: 1.0
 */
public interface IAdminFileService {
    String uploadFile(MultipartFile file);
}
