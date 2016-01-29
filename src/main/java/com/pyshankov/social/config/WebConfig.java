package com.pyshankov.social.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by pyshankov on 28.01.2016.
 */
@Configuration
@EnableWebMvc // enable spring mvc
@ComponentScan("com.pyshankov.social.web")
public class WebConfig {

}
