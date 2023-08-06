package com.fullcyccle.admin.catalogo.domain;

import com.fullcyccle.admin.catalogo.domain.validation.ValidationHandler;

public abstract class AggregateRoot<ID extends Identifier> extends Entity<ID> {
  
  protected AggregateRoot(ID id) {
    super(id);
  }
}
