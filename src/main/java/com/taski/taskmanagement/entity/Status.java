package com.taski.taskmanagement.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Status {

    @Id
    private String id;

    private String name;

    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status(String id, String name) {
        this.name = name;
        this.id = id;
    }
}
