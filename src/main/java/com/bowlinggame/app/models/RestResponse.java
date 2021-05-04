package com.bowlinggame.app.models;

public class RestResponse<T> {
    private boolean status;
    private T data;

    public RestResponse( boolean status,T data) {
        this.data = data;
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
