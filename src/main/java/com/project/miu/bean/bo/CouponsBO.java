package com.project.miu.bean.bo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CouponsBO {
    private long id;
    private String uuid;
    private String title;
    private long money;
    private String address;
    private String desc;
    private String categoryUuid;
    private String startTime;
    private String endTime;
    private String createTime;
    private String updateTime;
    private int delete;
}
