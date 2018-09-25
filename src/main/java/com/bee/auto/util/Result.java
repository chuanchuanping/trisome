package com.bee.auto.util;

public class Result {
   private Integer result_code;  //返回码
   private String result_msg;    //返回信息
   private String error_detail;     // 错误信息
   private Object data;          // 返回正常时需要返回的具体信息
   private Integer status;

   private String checkResult;

    public Result(String checkResult) {
        this.checkResult = checkResult;
    }

    public Result() {
    }

    public Result(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }


}
