package com.hari.sb.httpdemo.exception;


public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String msg){
       super(msg);
    }
}
