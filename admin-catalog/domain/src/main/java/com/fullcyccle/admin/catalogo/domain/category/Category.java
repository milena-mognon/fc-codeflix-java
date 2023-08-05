package com.fullcyccle.admin.catalogo.domain.category;

import java.time.Instant;
import java.util.UUID;

public class Category {
  private final UUID id;
  private final String name;
  private final String description;
  private final boolean active;
  private final Instant createdAt;
  private final Instant updatedAt;
  private final Instant deletedAt;
  // Instant (marco no tempo - preciso - UTC - não leva em conta o timezone) x LocaDateTime (data normal)
  // final - imutável
  
  private Category(
      final UUID id,
      final String name,
      final String description,
      final boolean active,
      final Instant createdAt,
      final Instant updatedAt,
      final Instant deletedAt
  ) {
    this.name = name;
    this.id = id;
    this.description = description;
    this.active = active;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.deletedAt = deletedAt;
  }
  
  public static Category newCategory(final String aName, final String sDescription, final boolean aActive) {
    final var id = UUID.randomUUID();
    final var now = Instant.now();
    return new Category(id, aName, sDescription, aActive, now, now, null);
  }
  
  public UUID getId() {
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