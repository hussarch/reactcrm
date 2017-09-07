package com.shhxzq.dc.scs.frm.core.domain;

/**
 * @author XiaoYi
 * Created on 2017-07-26 17:33:09
 */
public class CommonResponse<T>{
    
    public Boolean success;
    
    private T value;
    
    private String msg;
    
    public CommonResponse(){
    }
    
    public CommonResponse(boolean success, String msg){
        this.setMsg(success, msg);
    }
    
    public CommonResponse(boolean success, T value){
        this.setValue(success, value);
    }
    
    public Boolean getSuccess() {
        return success;
    }

    
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    
    public T getValue() {
        return value;
    }
    
    public void setValue(boolean success, T value) {
        this.success = success;
        this.value = value;
    }
    
    public void setValue(T value) {
        this.value = value;
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
