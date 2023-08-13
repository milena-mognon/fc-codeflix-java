package com.fullcyccle.admin.catalogo.application.category.update;

import com.fullcyccle.admin.catalogo.application.UseCase;
import com.fullcyccle.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase extends UseCase<UpdateCategoryCommand, Either<Notification,
    UpdateCategoryOutput>> {
}
