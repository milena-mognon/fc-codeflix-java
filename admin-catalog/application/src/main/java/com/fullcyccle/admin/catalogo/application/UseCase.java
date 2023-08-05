package com.fullcyccle.admin.catalogo.application;

import com.fullcyccle.admin.catalogo.domain.category.Category;

public class UseCase {
  public Category execute() {
    return new Category();
  }
}