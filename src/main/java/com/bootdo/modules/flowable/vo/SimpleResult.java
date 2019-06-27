package com.bootdo.modules.flowable.vo;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * API 返回Bean
 *
 * @author shareniu
 */
public class SimpleResult implements Serializable {

	private static final long serialVersionUID = 6278501798801287960L;
	protected String message = null;
    protected Integer result;
    protected Object data = null;
    protected String errorMsg;
    protected String errorCode;

    //简单响应页面
    public static SimpleResult responseOk(String message){
        SimpleResult simpleResult = new SimpleResult();
        simpleResult.setResult(1);
        simpleResult.setMessage(message);
        return simpleResult;
    }

    //简单响应页面
    public static SimpleResult responseError(String message){
        SimpleResult simpleResult = new SimpleResult();
        simpleResult.setResult(0);
        simpleResult.setMessage(message);
        return simpleResult;
    }



    public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
