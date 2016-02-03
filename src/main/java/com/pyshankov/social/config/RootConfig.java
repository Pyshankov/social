package com.pyshankov.social.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by pyshankov on 28.01.2016.
 */
@Configuration
@ComponentScan(basePackages={"com.pyshankov.social"},
        excludeFilters={
                @ComponentScan.Filter(type= FilterType.ANNOTATION, value=EnableWebMvc.class)
        })
@ImportResource("classpath:META-INF/root-config.xml")
public class RootConfig {
}
