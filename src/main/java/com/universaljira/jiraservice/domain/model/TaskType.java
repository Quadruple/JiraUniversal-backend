package com.universaljira.jiraservice.domain.model;

public enum TaskType {
    TODO("todo"),
    INPROGRESS("inprogress"),
    DONE("done");

    private String value;

    public String getValue() {
        return this.value;
    }

    TaskType(String value) {
        this.value = value;
    }
}