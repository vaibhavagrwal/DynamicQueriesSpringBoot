package com.sub.sublayer.service;


/*
    {
       'entity': 'subscription',
       'condition':
       [
            {
               "attribute":"status",
               "value": "active",
               "operator": "=",
               "joinType": "AND",
            }

            {
               "attribute":"startDate",
               "value": "20/02/2023",
               "operator": "<",
               "joinType": "AND",
            }
       ]
    }
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.x.protobuf.MysqlxExpr;
import com.sub.sublayer.models.QueryRequest;
import com.sub.sublayer.models.QueryResponse;
import com.sub.sublayer.repository.OrdersRepository;
import com.sub.sublayer.repository.SubsRepository;
import com.sub.sublayer.repository.UserRepository;
//import com.sub.sublayer.specifications.SpecificationsBuilder;
import com.sub.sublayer.specifications.SpecificationsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

//  path===>   subs->orders->user
//
//    {
//            "targetEntity": "user",   // Required Entity
//            "targetAttributes": ["name"],
//            "entityPath": ["order","user"],
//            "conditionEntity":"subs"
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

@Service
public class SearchService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    SubsRepository subsRepository;

    public QueryResponse getQueryResponse(QueryRequest queryRequest){

        System.out.println(queryRequest.toString());

        SpecificationsBuilder builder=new SpecificationsBuilder();

        for(int i=0;i<queryRequest.getConditions().size();i++){
            // System.out.println(queryRequest.getConditions().get(i).getOperation());
            builder.with(
                    queryRequest.getConditions().get(i).getJoinType(),
                    queryRequest.getConditions().get(i).getAttribute(),
                    queryRequest.getConditions().get(i).getOperation(),
                    queryRequest.getConditions().get(i).getValue()
            );
        }

        Specification spec=  builder.build();
//
//        UserSpecification spec =
//                new UserSpecification(new SearchCriteria("id", ":", 1));
        
        
        List<?> list;

        //System.out.println(queryRequest.getEntity());

        if(queryRequest.getConditionEntity().toString().equals("user"))
            list= userRepository.findAll(spec);
        else if(queryRequest.getConditionEntity().toString().equals("orders"))
            list= ordersRepository.findAll(spec);
        else
            list= subsRepository.findAll(spec);

        System.out.println(list);

        ObjectMapper mapper=new ObjectMapper();
        List<Map<String,Object>> mapList= new ArrayList<>();

        for(int i=0;i<list.size();i++){
            Map<String,Object> m= mapper.convertValue(list.get(i),Map.class);

//            System.out.println(mLarge.get("order"));
//            Map<String,Object> m=mapper.convertValue(mLarge.get(queryRequest.getEntity()),Map.class);
             System.out.println(m);
             System.out.println(queryRequest.getEntityPath());
            for(int j=0;j<queryRequest.getEntityPath().size();j++){
                m=mapper.convertValue(m.get(queryRequest.getEntityPath().get(j)),Map.class);
            }

            System.out.println("*****"+m);

            if(queryRequest.getEntityPath().size()>0)
            m=mapper.convertValue(m.get(queryRequest.getTargetEntity()),Map.class);

            Map<String,Object> mp= new HashMap<>();

            for(int j=0;j<queryRequest.getTargetAttributes().size();j++){
                mp.put(queryRequest.getTargetAttributes().get(j),m.get(queryRequest.getTargetAttributes().get(j)));
            }
             mapList.add(mp);
        }


        return QueryResponse
                .builder()
                .entity(queryRequest.getTargetEntity())
                .objectList(mapList)
                .build();
    }

//    public <T> List<Map<String,String>> getAttributes(List<T> list){
//        ObjectMapper mapper=new ObjectMapper();
//        List<Map<String,String>> mapList= new ArrayList<>();
//
//        for(int i=0;i<list.size();i++){
//            Map<String,Object> m= mapper.convertValue(list.get(i),Map.class)
//        }
//
//    }
}

