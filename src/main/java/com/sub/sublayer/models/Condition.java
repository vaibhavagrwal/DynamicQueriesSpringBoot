package com.sub.sublayer.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Condition {
    String attribute;
    Object value;
    String operation;
    String joinType;

    public Condition(String attribute, Object value, String operation, String joinType) {
        this.attribute = attribute;
        this.value = value;
        this.operation = operation;
        this.joinType = joinType;
    }

}

