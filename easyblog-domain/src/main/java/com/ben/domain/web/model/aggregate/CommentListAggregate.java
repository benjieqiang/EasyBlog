package com.ben.domain.web.model.aggregate;

import com.ben.domain.web.model.entity.CommentItemEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-03  11:27
 * @Description: 评论集合
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentListAggregate {
    /**
     * 评论总数
     */
    private Integer total;

    private List<CommentItemEntity> commentEntities;
}
