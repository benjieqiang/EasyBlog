package com.ben.types.response;

import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-12  15:29
 * @Description: 分页响应参数工具类
 * @Version: 1.0
 */
@Data
public class PageResponse<T> extends Response<List<T>> {

    /**
     * 总记录数
     */
    private Long total = 0L;

    /**
     * 每页显示的记录数，默认每页显示 10 条
     */
    private Integer size = 10;

    /**
     * 当前页码
     */
    private Integer current;

    /**
     * 总页数
     */
    private long pages;

    public static <T> PageResponse<T> success(long total, int current, int size, List<T> data) {
        PageResponse<T> response = new PageResponse<>();
        response.setSuccess(true);
        response.setCurrent(current);
        response.setSize(size);
        // 计算总页数
        int pages = (int) Math.ceil((double) total / size);
        response.setPages(pages);
        response.setTotal(total);
        response.setData(data);
        return response;
    }
}