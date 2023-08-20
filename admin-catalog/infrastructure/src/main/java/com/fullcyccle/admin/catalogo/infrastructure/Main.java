package com.fullcyccle.admin.catalogo.infrastructure;

import com.fullcyccle.admin.catalogo.application.UseCase;
import com.fullcyccle.admin.catalogo.infrastructure.configuration.WebServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

// muito importante adicionar essa anotação, o spring não irá funcionar sem ela
@SpringBootApplication
public class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    /**
     * SpringApplication deve receber uma classe que será utilizada para executar reflections
     * e pegar as configurações que devem ser utilizadas na hora de subir a aplicação
     * */
    // System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "development");
    System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "development");
    
    SpringApplication.run(WebServerConfig.class, args);
  }
}