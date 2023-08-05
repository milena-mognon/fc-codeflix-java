package com.fullcyccle.admin.catalogo.domain.category;

import com.fullcyccle.admin.catalogo.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class CategoryID extends Identifier {
  private final String value;
  
  private CategoryID(final String value) {
    // Constraint para validar que esse valor não pode ser null
    Objects.requireNonNull(value);
    this.value = value;
  }
  
  /** Factory method exposto para gerar o ID  */
  public static CategoryID unique() {
    return CategoryID.from(UUID.randomUUID());
  }
  
  /** Factory method exposto para converter o o valor de string para CategoryID  */
  public static CategoryID from(final String anId) {
    return new CategoryID(anId);
  }
  
  /** Factory method exposto para converter um UUID para CategoryID  */
  public static CategoryID from(final UUID anId) {
    return new CategoryID(anId.toString().toLowerCase());
  }
  
  /** Factory method exposto para retornar o valor  */
  public String getValue() {
    return value;
  }
  
  @Override
  public boolean equals(Object o) {
    if(this == o) return true;
    if(o == null || getClass() != o.getClass()) return false;
    final CategoryID that = (CategoryID) o;
    return Objects.equals(getValue(), that.getValue());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getValue());
  }
}
