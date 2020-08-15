package com.universaljira.jiraservice.domain.model;

public enum DropTarget {
    TODO("todo"),
    INPROGRESS("inprogress"),
    DONE("done");

    private String value;

    public String getValue() {
        return this.value;
    }

    DropTarget(String value) {
        this.value = value;
    }
}