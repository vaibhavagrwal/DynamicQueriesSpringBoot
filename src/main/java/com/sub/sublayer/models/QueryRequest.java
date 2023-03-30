package com.sub.sublayer.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


//    {
//            "targetEntity": "user",   // Required Entity
//            "targetAttributes": ["name"],
//            "entityPath": ["order","user"],
//            "conditionEntity":"subs" // start Entity
//            "conditions":
//            [
//                {
//                     "attribute":"id",
//                     "value": "1001",
//                     "operation": ":",
//                     "joinType": "AND"
//                }
//            ]
//    }
//

@Getter
@Setter
@Builder
public class QueryRequest {
    String targetEntity;
    List<String> targetAttributes;
    List<String> entityPath;
    String conditionEntity;
    List<Condition> conditions;
}
