package com.sub.sublayer.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class QueryResponse {
    String entity;
    List<Object> objectList;
}
