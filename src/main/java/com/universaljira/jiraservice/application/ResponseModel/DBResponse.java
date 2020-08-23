package com.universaljira.jiraservice.application.ResponseModel;

import lombok.Getter;

@Getter
public class DBResponse {
    private String result;

    public DBResponse(String result) {
        this.result = result;
    }
}