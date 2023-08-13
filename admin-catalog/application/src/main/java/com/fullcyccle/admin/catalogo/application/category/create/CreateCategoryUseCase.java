package com.fullcyccle.admin.catalogo.application.category.create;

import com.fullcyccle.admin.catalogo.application.UseCase;
import com.fullcyccle.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;

/**
 * Não trabalhe para uma implementação, trabalhe para uma abstração
 * Não acople o código na implementação, e sim na abstração
 */
public abstract class CreateCategoryUseCase extends UseCase<CreateCategoryCommand, Either<Notification,
    CreateCategoryOutput>> {
  // Como essa classe abstrata está estendendo de UseCase, quem estender ela obrigatóriamente terá que implementar o
  // método execute
}
