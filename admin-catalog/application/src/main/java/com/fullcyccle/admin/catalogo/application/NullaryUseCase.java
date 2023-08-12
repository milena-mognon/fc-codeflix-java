package com.fullcyccle.admin.catalogo.application;

public abstract class NullaryUseCase<OUTPUT> {
  /** Esse é um caso de uso que não recebe nada mas possui um retorno */
  public abstract OUTPUT execute();
}
