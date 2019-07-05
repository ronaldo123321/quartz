package com.example.demo;

import com.example.demo.apec.entity.QUser;
import com.google.common.collect.Lists;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }


    @Bean
    RestTemplate restTemplate(){
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(1000);
        requestFactory.setReadTimeout(1000);

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }

    //声称查询条件语句
    public Predicate testQmodel(){
        QUser qUser = QUser.user;
        List<BooleanExpression> myFilter = Lists.newArrayList();
        myFilter.add(qUser.username.like("%"+"aaa"+"%"));
        myFilter.add(qUser.nickName.eq("xiaohong"));

        return ExpressionUtils.allOf(myFilter.toArray(new BooleanExpression[myFilter.size()]));
    }

}
