package com.fullcyccle.admin.catalogo.domain.category;

import com.fullcyccle.admin.catalogo.domain.pagination.Pagination;

import java.util.Optional;

public interface CategoryGateway {
  Category create(Category aCategory);
  
  void deleteById(CategoryID anId);
  
  Optional<Category> findById(CategoryID anId); // Optional - Pode ou não encontrar uma categoria
  
  Category update(Category aCategory);
  
  Pagination<Category> findAll(CategorySearchQuery aQuery);
}
