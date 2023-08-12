package com.fullcyccle.admin.catalogo.domain.category;

import com.fullcyccle.admin.catalogo.domain.validation.Error;
import com.fullcyccle.admin.catalogo.domain.validation.ValidationHandler;
import com.fullcyccle.admin.catalogo.domain.validation.Validator;

/**
 * Validator não está dentro da entidade, pois ele tende a sofrer mais
 * modificações que a entidade.
 * Entretanto, para respeitar o DDD, a entidade precisa saber se validar,
 * por isso ele deve ser chamado dentro da entidade
 */
public class CategoryValidator extends Validator {
  public static final int NAME_MAX_LENGTH = 255;
  public static final int MIN_NAME_LENGTH = 3;
  private final Category category;
  
  public CategoryValidator(final Category aCategory, final ValidationHandler aHandler) {
    super(aHandler);
    this.category = aCategory;
  }
  
  @Override
  public void validate() {
    checkNameConstraints();
  }
  
  private void checkNameConstraints() {
    final var name = this.category.getName();
    
    if(name == null) {
      this.validationHandler().append(new Error("'name' should not be null"));
      return;
    }
    if(name.isBlank()) {
      this.validationHandler().append(new Error("'name' should not be empty"));
      return;
    }
    final int length = name.trim().length();
    if(length > NAME_MAX_LENGTH || length < MIN_NAME_LENGTH) {
      this.validationHandler().append(new Error("'name' must be between 3 and 255 characters"));
    }
  }
}
