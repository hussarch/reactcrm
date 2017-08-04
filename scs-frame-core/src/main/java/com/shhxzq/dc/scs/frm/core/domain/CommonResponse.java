package com.shhxzq.dc.scs.frm.core.domain;

/**
 * @author XiaoYi
 * Created on 2017-07-26 17:33:09
 */
public class CommonResponse<T>{
    
    public Boolean success;
    
    private T content;
    
    private String msg;
    
    public CommonResponse(){
    }
    
    public CommonResponse(boolean success, String msg){
        this.setMsg(success, msg);
    }
    
    public CommonResponse(boolean success, T content){
        this.setContent(success, content);
    }
    
    public Boolean getSuccess() {
        return success;
    }

    
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    
    public T getContent() {
        return content;
    }
    
    public void setContent(boolean success, T content) {
        this.success = success;
        this.content = content;
    }
    
    public void setContent(T content) {
        this.content = content;
    }

    
    public String getMsg() {
        return msg;
    }

    public void setMsg(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }

}
