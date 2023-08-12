package com.fullcyccle.admin.catalogo.application;

public abstract class UnitUseCase<INPUT> {
  /** Esse é um caso de uso que não tem retorno */
  public abstract void execute(INPUT aIn);
}
