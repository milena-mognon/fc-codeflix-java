package com.fullcyccle.admin.catalogo.application.category.retrive.get;

import com.fullcyccle.admin.catalogo.domain.category.CategoryGateway;
import com.fullcyccle.admin.catalogo.domain.category.CategoryID;
import com.fullcyccle.admin.catalogo.domain.exceptions.DomainException;
import com.fullcyccle.admin.catalogo.domain.validation.Error;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetCategoryByIdUseCase extends GetCategoryByIdUseCase {
  private final CategoryGateway categoryGateway;
  
  public DefaultGetCategoryByIdUseCase(final CategoryGateway categoryGateway) {
    this.categoryGateway = Objects.requireNonNull(categoryGateway);
  }
  
  @Override
  public CategoryOutput execute(String anId) {
    final var aCategoryId = CategoryID.from(anId);
    return this.categoryGateway.findById(aCategoryId)
        .map(CategoryOutput::from) // cai no map se a busca no banco de dados retornou valor
        .orElseThrow(notFound(aCategoryId)); // cai no else se não encontrou nada
  }
  
  private static Supplier<DomainException> notFound(final CategoryID anId) {
    return () -> DomainException.with(
        new Error("Category with ID %s was not found".formatted(anId.getValue()))
    );
  }
}
