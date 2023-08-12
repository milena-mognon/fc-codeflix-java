package com.fullcyccle.admin.catalogo.application;

import com.fullcyccle.admin.catalogo.domain.category.Category;

public abstract class UseCase<INPUT, OUTPUT> {
  /** Na literatura, por padrão os casos de uso implementam o pattern Command.
   *  O Pattern Command carrega a semântica no nome da classe (o que a classe faz)
   *  e tem um único método public chamado execute */
  public abstract OUTPUT execute(INPUT aIn);
}