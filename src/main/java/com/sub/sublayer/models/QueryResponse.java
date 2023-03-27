package com.sub.sublayer.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Builder
@Getter
@Setter
public class QueryResponse {
    String entity;
    List<Map<String,String>> objectList;
}
