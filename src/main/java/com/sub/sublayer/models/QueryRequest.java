package com.sub.sublayer.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QueryRequest {
    String entity;
    List<Condition> conditions;

    public QueryRequest(String entity, List<Condition> conditions) {
        this.entity = entity;
        this.conditions = conditions;
    }
}
