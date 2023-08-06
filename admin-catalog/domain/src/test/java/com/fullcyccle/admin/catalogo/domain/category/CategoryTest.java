package com.fullcyccle.admin.catalogo.domain.category;

import com.fullcyccle.admin.catalogo.domain.exceptions.DomainException;
import com.fullcyccle.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {
  @Test
  public void givenAValidParams_whenCallNewCategory_thenInstantiateACategory() {
    final var expectedName = "Filmes";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;
    final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
    
    Assertions.assertNotNull(actualCategory);
    Assertions.assertNotNull(actualCategory.getId());
    Assertions.assertEquals(expectedName, actualCategory.getName());
    Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
    Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
    
    Assertions.assertNotNull(actualCategory.getCreatedAt());
    Assertions.assertNotNull(actualCategory.getUpdatedAt());
    Assertions.assertNull(actualCategory.getDeletedAt());
  }
  
  @Test
  public void givenAnInvalidNullName_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
    final String expectedName = null;
    final var expectedErrorMessage = "'name' should not be null";
    final var expectedErrorCount = 1;
    
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;
    
    final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
    
    final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));
    
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
  }
  
  @Test
  public void givenAnInvalidEmptyName_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
    final String expectedName = "  ";
    final var expectedErrorMessage = "'name' should not be empty";
    final var expectedErrorCount = 1;
    
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;
    
    final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
    
    final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));
    
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
  }
  
  @Test
  public void givenAnInvalidNameLessThan3_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
    final String expectedName = "Fi ";
    final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
    final var expectedErrorCount = 1;
    
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;
    
    final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
    
    final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));
    
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
  }
  
  @Test
  public void givenAnInvalidNameMoreThan255_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
    final String expectedName = """
    Lorem ipsum dolor sit amet, consectetur adipiscing elit.
    Nullam aliquet leo diam, sit amet finibus diam eleifend at.
    Sed ut enim sit amet nunc aliquam rutrum in eu justo.
    Donec pellentesque nulla eu massa finibus rhoncus.
    Sed ultricies nisi vulputate leo auctor, ut iaculis dolor vulputate.""";
    final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
    final var expectedErrorCount = 1;
    
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;
    
    final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
    
    final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));
    
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
  }
  @Test
  public void givenAValidEmptyDescription_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
    final var expectedName = "Filmes";
    final var expectedDescription = "  ";
    final var expectedIsActive = true;
    final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
    
    Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));
   
    Assertions.assertNotNull(actualCategory);
    Assertions.assertNotNull(actualCategory.getId());
    Assertions.assertEquals(expectedName, actualCategory.getName());
    Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
    Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
    
    Assertions.assertNotNull(actualCategory.getCreatedAt());
    Assertions.assertNotNull(actualCategory.getUpdatedAt());
    Assertions.assertNull(actualCategory.getDeletedAt());
  }
  
  @Test
  public void givenAValidFalseIsActive_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
    final var expectedName = "Filmes";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = false;
    final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
    
    Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));
    
    Assertions.assertNotNull(actualCategory);
    Assertions.assertNotNull(actualCategory.getId());
    Assertions.assertEquals(expectedName, actualCategory.getName());
    Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
    Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
    
    Assertions.assertNotNull(actualCategory.getCreatedAt());
    Assertions.assertNotNull(actualCategory.getUpdatedAt());
    Assertions.assertNotNull(actualCategory.getDeletedAt());
  }
  
  @Test
  public void givenAValidActiveCategory_whenCallDeactivate_thenReturnCategoryInactivated() {
    final var expectedName = "Filmes";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = false;
    final var aCategory = Category.newCategory(expectedName, expectedDescription, true);
    
    Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));
    
    final var createdAt = aCategory.getCreatedAt();
    final var updatedAt = aCategory.getUpdatedAt();
    
    Assertions.assertNull(aCategory.getDeletedAt());
    Assertions.assertTrue(aCategory.isActive());
    
    final var actualCategory = aCategory.deactivate();
    
    Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));
    
    Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
    Assertions.assertEquals(expectedName, actualCategory.getName());
    Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
    Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
    
    Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
    Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
    Assertions.assertNotNull(actualCategory.getDeletedAt());
  }
  
  @Test
  public void givenAValidInactiveCategory_whenCallActivate_thenReturnCategoryActivated() {
    final var expectedName = "Filmes";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;
    
    final var aCategory = Category.newCategory(expectedName, expectedDescription, false);
    
    Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));
    
    final var createdAt = aCategory.getCreatedAt();
    final var updatedAt = aCategory.getUpdatedAt();
    
    Assertions.assertNotNull(aCategory.getDeletedAt());
    Assertions.assertFalse(aCategory.isActive());
    
    final var actualCategory = aCategory.activate();
    
    Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));
    
    Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
    Assertions.assertEquals(expectedName, actualCategory.getName());
    Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
    Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
    
    Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
    Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
    Assertions.assertNull(actualCategory.getDeletedAt());
  }
}