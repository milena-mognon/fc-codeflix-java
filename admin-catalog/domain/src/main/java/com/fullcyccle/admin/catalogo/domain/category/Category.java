package com.fullcyccle.admin.catalogo.domain.category;

import com.fullcyccle.admin.catalogo.domain.AggregateRoot;
import com.fullcyccle.admin.catalogo.domain.validation.ValidationHandler;

import java.time.Instant;

public class Category extends AggregateRoot<CategoryID> {
  private final String name;
  private final String description;
  private final boolean active;
  private final Instant createdAt;
  private final Instant updatedAt;
  private final Instant deletedAt;
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
  
  @Override
  public void validate(final ValidationHandler handler) {
    new CategoryValidator(this, handler).validate();
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
}