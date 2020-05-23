package com.project.miu.bean.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: gelj
 * @Date: 2020/5/21 1:47 下午
 */
@Data
public class PageResult<T> {
    /**
     * 分页返回
     * "data": {
     * "total": 0,
     * "rows": [
     * {
     * "id": "string",
     * "labelname": "string",
     * "state": "string",
     * "count": 0,
     * "recommend": "string"
     * }
     * ]
     * }
     */
    private long totalPages;
    private List<T> list;

    public PageResult(long totalPages, List<T> content) {
        this.totalPages = totalPages;
        this.list = content;
    }
}
