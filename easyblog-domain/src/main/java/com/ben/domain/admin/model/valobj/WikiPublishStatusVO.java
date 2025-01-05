package com.ben.domain.admin.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WikiPublishStatusVO {

    NOT_PUBLISHED(0),
    PUBLISHED(1);

    private Integer status;

}