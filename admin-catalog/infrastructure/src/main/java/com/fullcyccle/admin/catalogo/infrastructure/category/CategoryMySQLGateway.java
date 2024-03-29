package com.fullcyccle.admin.catalogo.infrastructure.category;

import com.fullcyccle.admin.catalogo.domain.category.Category;
import com.fullcyccle.admin.catalogo.domain.category.CategoryGateway;
import com.fullcyccle.admin.catalogo.domain.category.CategoryID;
import com.fullcyccle.admin.catalogo.domain.category.CategorySearchQuery;
import com.fullcyccle.admin.catalogo.domain.pagination.Pagination;
import com.fullcyccle.admin.catalogo.infrastructure.category.persistence.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryMySQLGateway implements CategoryGateway {
  
  private final CategoryRepository repository;
  
  public CategoryMySQLGateway(final CategoryRepository repository) {
    this.repository = repository;
  }
  
  @Override
  public Category create(Category aCategory) {
    return null;
  }
  
  @Override
  public void deleteById(CategoryID anId) {
  
  }
  
  @Override
  public Optional<Category> findById(CategoryID anId) {
    return Optional.empty();
  }
  
  @Override
  public Category update(Category aCategory) {
    return null;
  }
  
  @Override
  public Pagination<Category> findAll(CategorySearchQuery aQuery) {
    return null;
  }
}
