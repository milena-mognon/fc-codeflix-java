package com.fullcyccle.admin.catalogo.application.category.retrive.list;

import com.fullcyccle.admin.catalogo.application.UseCase;
import com.fullcyccle.admin.catalogo.domain.category.CategorySearchQuery;
import com.fullcyccle.admin.catalogo.domain.pagination.Pagination;

public abstract class ListCategoriesUseCase
    extends UseCase<CategorySearchQuery, Pagination<CategoryListOutput>> {
}
