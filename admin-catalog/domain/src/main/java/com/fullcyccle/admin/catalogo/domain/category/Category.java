package com.fullcyccle.admin.catalogo.domain.category;

import com.fullcyccle.admin.catalogo.domain.AggregateRoot;
import com.fullcyccle.admin.catalogo.domain.validation.ValidationHandler;

import java.time.Instant;

public class Category extends AggregateRoot<CategoryID> implements Cloneable {
  private String name;
  private String description;
  private boolean active;
  private Instant createdAt;
  private Instant updatedAt;
  private Instant deletedAt;
  // Instant (marco no tempo - preciso - UTC - não leva em conta o timezone) x LocaDateTime (data normal)
  // final - imutável
  
  public Category(
      final CategoryID anId,
      final String aName,
      final String aDescription,
      final boolean isActive,
      final Instant aCreationDate,
      final Instant aUpdateDate,
      final Instant aDeleteDate
  ) {
    super(anId);
    this.name = aName;
    this.description = aDescription;
    this.active = isActive;
    this.createdAt = aCreationDate;
    this.updatedAt = aUpdateDate;
    this.deletedAt = aDeleteDate;
  }
  
  public static Category newCategory(final String aName, final String aDescription, final boolean isActive) {
    final var id = CategoryID.unique();
    final var now = Instant.now();
    final var deletedAt = isActive ? null : now;
    return new Category(id, aName, aDescription, isActive, now, now, deletedAt);
  }
  
  public static Category with(
      final CategoryID anId,
      final String name,
      final String description,
      final boolean active,
      final Instant createdAt,
      final Instant updatedAt,
      final Instant deletedAt) {
    return new Category(
        anId,
        name,
        description,
        active,
        createdAt,
        updatedAt,
        deletedAt
    );
  }
  
  public static Category with(final Category aCategory) {
    return with(
        aCategory.getId(),
        aCategory.getName(),
        aCategory.getDescription(),
        aCategory.isActive(),
        aCategory.getCreatedAt(),
        aCategory.getUpdatedAt(),
        aCategory.getDeletedAt()
    );
  }
  
  @Override
  public void validate(final ValidationHandler handler) {
    new CategoryValidator(this, handler).validate();
  }
  
  public Category deactivate() {
    if(getDeletedAt() == null) {
      this.deletedAt = Instant.now();
    }
    this.active = false;
    this.updatedAt = Instant.now();
    return this;
  }
  
  public Category activate() {
    this.deletedAt = null;
    this.active = true;
    this.updatedAt = Instant.now();
    return this;
  }
  
  public Category update(
      final String aName,
      final String aDescription,
      final boolean isActive
  ) {
    if(isActive) {
      activate();
    } else {
      deactivate();
    }
    this.name = aName;
    this.description = aDescription;
    this.updatedAt = Instant.now();
    return this;
  }
  
  public CategoryID getId() {
    return id;
  }
  
  public String getName() {
    return name;
  }
  
  public String getDescription() {
    return description;
  }
  
  public boolean isActive() {
    return active;
  }
  
  public Instant getCreatedAt() {
    return createdAt;
  }
  
  public Instant getUpdatedAt() {
    return updatedAt;
  }
  
  public Instant getDeletedAt() {
    return deletedAt;
  }
  
  // cria um novo objeto clonando os dados da categoria
  @Override
  public Category clone() {
    try {
      return (Category) super.clone();
    } catch(CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }
}