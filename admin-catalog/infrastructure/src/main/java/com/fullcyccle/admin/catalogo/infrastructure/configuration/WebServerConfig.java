package com.fullcyccle.admin.catalogo.infrastructure.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration // diz que é uma classe de configuração
 * @ComponentScan("com.fullcyccle.admin.catalogo") // diz qual é o package padrão que ele precisa analisar para
 * // procurar as classes que possuem anotações que ele usa para gerar um bin
 * O que é um bin do spring = classe java que o spring vai instanciar e usar para injetar em outras classes que
 * dependem dessa
 */


@Configuration
@ComponentScan("com.fullcyccle.admin.catalogo")
public class WebServerConfig {
}
