package com.thankgod.client;

public class ResponseDto<T, U> {
  private T data;
  
  private U error;

  public T getData() {
    return data;
  }

  public U getError() {
    return error;
  }
  
  public void setData(T data) {
    this.data = data;
  }

  public void setError(U error) {
    this.error = error;
  }
  
  public static <T> ResponseDto success(T data) {
    final ResponseDto res = new ResponseDto();
    res.setData(data);
    return res;
  }
  
  public static <U> ResponseDto error(U error) {
    final ResponseDto res = new ResponseDto();
    res.setError(error);
    return res;
  }
}
