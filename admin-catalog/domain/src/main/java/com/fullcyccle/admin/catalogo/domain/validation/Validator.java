package com.fullcyccle.admin.catalogo.domain.validation;

public abstract class Validator {
  /** Propriedade ValidationHandler */
  private final ValidationHandler handler;
  
  /** Precisa receber o handler no construtor */
  protected Validator(final ValidationHandler aHandler) {
    this.handler = aHandler;
  }
  
  public abstract void validate();
  
  /** método helper para retornar o handler de maneira fluente */
  protected ValidationHandler validationHandler() {
    return this.handler;
  }
  
}
