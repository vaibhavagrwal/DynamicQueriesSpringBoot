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
import com.sub.sublayer.repository.UserRepository;
//import com.sub.sublayer.specifications.SpecificationsBuilder;
import com.sub.sublayer.specifications.SpecificationsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrdersRepository ordersRepository;

    public QueryResponse getQueryResponse(QueryRequest queryRequest){
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

        Specification spec= builder.build();
//
//        UserSpecification spec =
//                new UserSpecification(new SearchCriteria("id", ":", 1));
        
        
        List<?> list;

        //System.out.println(queryRequest.getEntity());

        if(queryRequest.getEntity().toString().equals("user"))
            list= userRepository.findAll(spec);
        else
            list= ordersRepository.findAll(spec);

        ObjectMapper mapper=new ObjectMapper();
        List<Map<String,String>> mapList= new ArrayList<>();

        for(int i=0;i<list.size();i++){
            Map<String,Object> m= mapper.convertValue(list.get(i),Map.class);
            Map<String,String> mp= new HashMap<>();

            for(int j=0;j<queryRequest.getAttributes().size();j++){
                mp.put(queryRequest.getAttributes().get(j),m.get(queryRequest.getAttributes().get(j)).toString());
            }
            mapList.add(mp);
        }

        return QueryResponse
                .builder()
                .entity(queryRequest.getEntity())
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

