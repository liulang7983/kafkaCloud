package com.bean;

/**
 * @Author ming.li
 * @Date 2024/4/29 10:34
 * @Version 1.0
 */
public class Msg {
    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Msg(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
