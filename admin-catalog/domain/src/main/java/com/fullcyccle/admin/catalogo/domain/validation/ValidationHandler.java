package com.fullcyccle.admin.catalogo.domain.validation;

import java.util.List;

public interface ValidationHandler {
  /**
   * Interface fluente - possibilidade de chamar um método, retornar a própria instância e encadear chamadas de
   * métodos
   */
  ValidationHandler append(Error anError);
  
  ValidationHandler append(ValidationHandler anHandler);
  
  ValidationHandler validate(Validation aValidation);
  
  /** Lista de Erros - deve ser implementado pelas classes*/
  List<Error> getErrors();
  
  default boolean hasErrors() {
    return getErrors() !=  null && !getErrors().isEmpty();
  }
  
  public interface Validation {
    void validate();
  }
}
