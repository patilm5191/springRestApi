package com.neu.api;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration      // using core features, we are not using web feature,
@EnableWebMvc       // indicates that application is going to receive apis
@ComponentScan
public class WebConfig {

}
