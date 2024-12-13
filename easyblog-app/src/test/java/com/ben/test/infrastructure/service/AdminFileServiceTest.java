package com.ben.test.infrastructure.service;

import com.ben.domain.admin.service.IAdminFileService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  17:14
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminFileServiceTest {

    @Autowired
    private IAdminFileService service;
    @Test
    public void test_upload() {
        // Create a mock MultipartFile
        MultipartFile mockFile = new MockMultipartFile(
                "test-file.txt",                         // File name
                "test-file.txt",                         // Original file name
                "text/plain",                            // Content type
                "This is a test file.".getBytes()        // File content
        );
        String result = service.uploadFile(mockFile);
        log.info("测试结果：{}", result);
    }
}
