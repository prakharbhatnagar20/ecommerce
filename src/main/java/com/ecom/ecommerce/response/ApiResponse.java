package com.ecom.ecommerce.response;


public class ApiResponse {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    private String message;
    private Boolean status;


    public ApiResponse(String message, Boolean status) {

        this.status=status;
        this.message=message;
    }

    public ApiResponse() {
    }
}
