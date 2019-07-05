package com.example.demo.apec.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author ronaldo
 * @date 2019/6/24 15:59
 * @description
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class RestRpcTest {


    @Autowired
    private RestTemplate restTemplate;


    public static final String  url  =   "http://192.168.7.10:22006/url/test";
    //public static final String  url  =   "http://hrms-news-service-gzf/url/test";


    @PostMapping("/url")
    public String test(){
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
//        map.add("email", "first.last@example.com");
//
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
//
//        ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class);
//
//
//
//        log.info(response.getBody());



        String   result = restTemplate.postForObject(url.trim(), new HttpEntity<>("test"), String.class);


        return result;
    }

}
