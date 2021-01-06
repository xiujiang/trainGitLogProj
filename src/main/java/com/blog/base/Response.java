package com.blog.base;

import com.alibaba.fastjson.JSONObject;
import com.blog.exception.ServiceException;
import lombok.Data;

import java.util.concurrent.Executors;

/**
 * author:xiujiang.liu
 * Date:2018/12/16
 * Time:10:06
 */

@Data
public class Response<T> {
    private String code;
    private String msg;
    private T data;

    public Response(String code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Response(){
    }

    public Response success(T data){
        return new Response("00","success",data);
    }
    public Response error(String err,T data){
        return new Response("10",err,data);
    }
    public Response error(Exception e,T data){
        if(e instanceof ServiceException){
            return new Response(((ServiceException) e).getCode(),e.getMessage(),data);
        }else{
            return new Response("10",e.getMessage(),data);
        }
    }
    @Override
    public String toString() {
        return "Response{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
