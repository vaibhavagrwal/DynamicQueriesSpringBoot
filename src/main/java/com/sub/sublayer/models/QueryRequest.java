package com.sub.sublayer.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class QueryRequest {
    String entity;
    List<String> attributes;
    List<Condition> conditions;
}
