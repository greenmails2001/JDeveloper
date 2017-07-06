package com.vc.web;

import java.io.Serializable;

import javax.faces.FacesException;

public class PerpSystemException extends Exception implements Serializable {
    private PerpSystemErrorCode errorCode;

    public PerpSystemException(PerpSystemErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public PerpSystemException(PerpSystemErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorCode(PerpSystemErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public PerpSystemErrorCode getErrorCode() {
        return errorCode;
    }
    
    public String toString() {
        return this.errorCode.toString()+":"+this.getMessage();
    }
}
