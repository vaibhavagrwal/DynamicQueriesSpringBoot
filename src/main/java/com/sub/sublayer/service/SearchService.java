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

import com.sub.sublayer.models.QueryRequest;
import com.sub.sublayer.models.QueryResponse;
import com.sub.sublayer.models.User;
import com.sub.sublayer.repository.UserRepository;
import com.sub.sublayer.specifications.SpecificationsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SearchService {
    @Autowired
    UserRepository userRepository;
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

        List<User> list= userRepository.findAll(spec);

        return QueryResponse.builder().entity("user").objectList(Collections.singletonList(list)).build();
    }
}
