package com.fullcyccle.admin.catalogo.application.category.create;

import com.fullcyccle.admin.catalogo.domain.category.Category;
import com.fullcyccle.admin.catalogo.domain.category.CategoryGateway;
import com.fullcyccle.admin.catalogo.domain.validation.handler.Notification;
import com.fullcyccle.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {
  private final CategoryGateway categoryGateway;
  
  public DefaultCreateCategoryUseCase(final CategoryGateway categoryGateway) {
    this.categoryGateway = Objects.requireNonNull(categoryGateway);
  }
  
  @Override
  public Either<Notification, CreateCategoryOutput> execute(final CreateCategoryCommand aCommand) {
    final var aName = aCommand.name();
    final var aDescription = aCommand.description();
    final var isActive = aCommand.isActive();
    
    final var notification = Notification.create();
    
    final var aCategory = Category.newCategory(aName, aDescription, isActive);
    aCategory.validate(notification);
    
    // Com a lib vavr é possível retornar algo Left ou Right
    return notification.hasErrors() ? API.Left(notification) :
        create(aCategory);
  }
  
  private Either<Notification, CreateCategoryOutput> create(final Category aCategory) {
    // Opção 1
    return API.Try(() -> this.categoryGateway.create(aCategory))
        .toEither()
        .bimap(Notification::create, CreateCategoryOutput::from);
    
    /*
    Todas as opções fazem a mesma coisa
     Opção 2
     return API.Try(() -> this.categoryGateway.create(aCategory))
         .toEither()
         .map(CreateCategoryOutput::from)
         .mapLeft(Notification::create);
         
     Opção 3
     return API.Right(CreateCategoryOutput.from(this.categoryGateway.create(aCategory)));
     
     Opção 4
     try {
      return API.Right(CreateCategoryOutput.from(this.categoryGateway.create(aCategory)));
    } catch(Throwable t) {
      return API.Left(Notification.create(t));
    }
    */
    
  }
}
