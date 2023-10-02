package com.tw.hexagon.ddd_sample.adapter;

import ch.vorburger.mariadb4j.springboot.autoconfigure.DataSourceAutoConfiguration;
import ch.vorburger.mariadb4j.springboot.autoconfigure.MariaDB4jSpringConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MariaDB4jSpringConfiguration.class, DataSourceAutoConfiguration.class})
public class MariaDBEmbeddedDBConfig {
}
