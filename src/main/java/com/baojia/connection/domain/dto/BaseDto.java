package com.baojia.connection.domain.dto;

import java.io.Serializable;

/**
 * @author wxr
 * @Title: ${file_name}
 * @Description: ${todo}
 * @date 2018/5/22 18:18
 */
public class BaseDto implements Serializable{
    //第几页
    private Integer pageNum = 1;
    //每页几条
    private Integer pageSize = 10;
    //查询字段类别
    private String conditionType;
    //关键字搜索
    private String keyWords;

    //token
    private String managerToken;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getConditionType() {
        return conditionType;
    }

    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getManagerToken() {
        return managerToken;
    }

    public void setManagerToken(String managerToken) {
        this.managerToken = managerToken;
    }
}
