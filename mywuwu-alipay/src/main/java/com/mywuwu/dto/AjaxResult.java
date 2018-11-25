package com.mywuwu.dto;

/**
 * Package: com.mywuwu.quartz.dto
 * Description： TODO
 * Author: 梁乐乐
 * Date: Created in 2018/5/20 20:20
 * Company: clinbrain
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 */
public class AjaxResult {

    /**
     * 编号  0：成功 1失败
     */
    private String code;
    /**
     * 消息信息
     */
    private String message;

    /**
     * 返回对象
     */
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
