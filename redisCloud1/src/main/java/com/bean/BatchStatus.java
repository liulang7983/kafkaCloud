package com.bean;

/**
 * @Author ming.li
 * @Date 2024/3/20 16:09
 * @Version 1.0
 */
public class BatchStatus {
    private String batchId;
    private Integer status;

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
