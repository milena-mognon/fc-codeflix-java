package com.fullcyccle.admin.catalogo.infrastructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Driver;

public class MainTest {
  @Test
  public void testMain() {
    Assertions.assertNotNull(new Main());
    Main.main(new String[]{});
  }
}
