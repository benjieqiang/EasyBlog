package com.ben.infrastructure.persistent.repository;

import com.ben.domain.admin.repository.IAdminFileRepository;
import com.ben.infrastructure.persistent.minio.MinioUtil;
import com.ben.types.enums.ResponseCode;
import com.ben.types.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  17:07
 * @Description: 文件上传
 * @Version: 1.0
 */
@Slf4j
@Component
public class AdminFileRepository implements IAdminFileRepository {
    @Autowired
    private MinioUtil minioUtil;

    @Override
    public String uploadFile(MultipartFile file) {
        String result;
        try {
            result = minioUtil.uploadFile(file);
        } catch (Exception e) {
            log.error("==> 上传文件至 Minio 错误: ", e);
            // 手动抛出业务异常，提示 “文件上传失败”
            throw new BizException(ResponseCode.FILE_UPLOAD_FAILED);
        }

        return result;
    }
}
