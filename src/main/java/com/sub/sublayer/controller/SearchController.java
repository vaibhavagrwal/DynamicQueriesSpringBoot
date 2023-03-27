package com.sub.sublayer.controller;

import com.sub.sublayer.models.QueryRequest;
import com.sub.sublayer.models.QueryResponse;
import com.sub.sublayer.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SearchController {
    @Autowired
    SearchService searchService;

    @PostMapping(value = "/search")
    public ResponseEntity<QueryResponse> searchEntity(@RequestBody QueryRequest queryRequest){
        QueryResponse queryRequestResponse=searchService.getQueryResponse(queryRequest);
        //System.out.println("ok");
        return ResponseEntity.status(200).body(queryRequestResponse);
    }

//    @GetMapping(value = "/search")
//    public ResponseEntity<QueryResponse> searchEntity(){
//        QueryResponse queryRequestResponse=searchService.getQueryResponse();
//        //System.out.println("ok");
//        return ResponseEntity.status(200).body(queryRequestResponse);
//    }

    @GetMapping("/health")
    public String health() {
        return "ok";
    }
}
