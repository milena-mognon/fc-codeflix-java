package com.fullcyccle.admin.catalogo.application.category.retrive.list;

import com.fullcyccle.admin.catalogo.domain.category.CategoryGateway;
import com.fullcyccle.admin.catalogo.domain.category.CategorySearchQuery;
import com.fullcyccle.admin.catalogo.domain.pagination.Pagination;

import java.util.Objects;

public class DefaultListCategoriesUseCase extends ListCategoriesUseCase {
  private final CategoryGateway categoryGateway;
  
  public DefaultListCategoriesUseCase(final CategoryGateway categoryGateway) {
    this.categoryGateway = Objects.requireNonNull(categoryGateway);
  }
  
  @Override
  public Pagination<CategoryListOutput> execute(CategorySearchQuery aQuery) {
    return this.categoryGateway.findAll(aQuery)
        .map(CategoryListOutput::from);
  }
}
