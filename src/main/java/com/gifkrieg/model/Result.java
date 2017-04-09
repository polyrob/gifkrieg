package com.gifkrieg.model;

import java.security.Principal;
import java.util.Map;

/**
 * Created by robbie on 4/8/17.
 */
public class Result {

    private String status;
    private Map<String, String> errors;
    private GKUserDetails principal;

    public Result(String status) {
        this.status = status;
    }

    public Result(String status, Map<String, String> errors) {
        this.status = status;
        this.errors = errors;
    }

    public Result(String status, GKUserDetails principal) {
        this.status = status;
        this.principal = principal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public GKUserDetails getPrincipal() {
        return principal;
    }

    public void setPrincipal(GKUserDetails principal) {
        this.principal = principal;
    }
}
