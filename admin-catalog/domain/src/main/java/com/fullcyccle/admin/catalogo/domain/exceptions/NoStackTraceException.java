package com.fullcyccle.admin.catalogo.domain.exceptions;

/** Exception sem stack trace - ajuda na performance da aplicação */
public class NoStackTraceException extends RuntimeException {
  public NoStackTraceException(final String message) {
    this(message, null);
  }
  
  public NoStackTraceException(final String message, final Throwable cause) {
    super(message, cause, true, false);
  }
}
