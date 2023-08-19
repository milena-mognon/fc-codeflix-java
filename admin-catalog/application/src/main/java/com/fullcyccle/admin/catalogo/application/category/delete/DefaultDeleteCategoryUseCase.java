package com.fullcyccle.admin.catalogo.application.category.delete;

import com.fullcyccle.admin.catalogo.domain.category.CategoryGateway;
import com.fullcyccle.admin.catalogo.domain.category.CategoryID;

import java.util.Objects;

public class DefaultDeleteCategoryUseCase extends DeleteCategoryUseCase {
  private final CategoryGateway categoryGateway;
  
  public DefaultDeleteCategoryUseCase(final CategoryGateway categoryGateway) {
    this.categoryGateway = Objects.requireNonNull(categoryGateway);
  }
  
  @Override
  public void execute(String aIn) {
    this.categoryGateway.deleteById(CategoryID.from(aIn));
  }
}
