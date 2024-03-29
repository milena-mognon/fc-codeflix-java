package com.fullcyccle.admin.catalogo.infrastructure.category;

import com.fullcyccle.admin.catalogo.infrastructure.MySQLGatewayTest;
import com.fullcyccle.admin.catalogo.infrastructure.category.persistence.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.*;
import java.util.Collection;

@MySQLGatewayTest
public class CategoryMySQLGatewayTest {
  
  @Autowired
  private CategoryMySQLGateway categoryGateway;
  
  @Autowired
  private CategoryRepository categoryRepository;
  
  @Test
  public void testInjectedDependencies() {
    Assertions.assertNotNull(categoryGateway);
    Assertions.assertNotNull(categoryRepository);
  }
}
