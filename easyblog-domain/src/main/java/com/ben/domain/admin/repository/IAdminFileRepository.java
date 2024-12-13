package com.ben.domain.admin.repository;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  17:06
 * @Description: 文件上传仓储
 * @Version: 1.0
 */
public interface IAdminFileRepository {
    String uploadFile(MultipartFile file);
}
