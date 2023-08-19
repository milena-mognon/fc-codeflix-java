package com.fullcyccle.admin.catalogo.application.category.retrive.get;

import com.fullcyccle.admin.catalogo.domain.category.Category;
import com.fullcyccle.admin.catalogo.domain.category.CategoryGateway;
import com.fullcyccle.admin.catalogo.domain.category.CategoryID;
import com.fullcyccle.admin.catalogo.domain.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetCategoryByIdUseCaseTest {
  
  @InjectMocks
  private DefaultGetCategoryByIdUseCase useCase;
  
  @Mock
  private CategoryGateway categoryGateway;
  
  @BeforeEach
  void cleanUp() {
    reset();
  }
  
  @Test
  public void givenAValidId_whenCallsGetCategoryCategory_shouldReturnCategory() {
    final var expectedName = "Filmes";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;
    
    final var aCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
    final var expectedId = aCategory.getId();
    
    when(categoryGateway.findById(eq(expectedId)))
        .thenReturn(Optional.of(aCategory.clone()));
    
    final var actualCategory = useCase.execute(expectedId.getValue());
    
    Assertions.assertEquals(expectedId, actualCategory.id());
    Assertions.assertEquals(expectedName, actualCategory.name());
    Assertions.assertEquals(expectedDescription, actualCategory.description());
    Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
    Assertions.assertEquals(aCategory.getCreatedAt(), actualCategory.createdAt());
    Assertions.assertEquals(aCategory.getUpdatedAt(), actualCategory.updatedAt());
    Assertions.assertEquals(aCategory.getDeletedAt(), actualCategory.deletedAt());
    
    verify(categoryGateway, times(1)).findById(eq(expectedId));
  }
  
  @Test
  public void givenAInvalidId_whenCallsGetCategory_shouldReturnNotFound() {
    final var expectedId = CategoryID.from("123");
    final var expectedErrorMessage = "Category with ID 123 was not found";
    final var expectedErrorCount = 1;
    
    when(categoryGateway.findById(eq(expectedId)))
        .thenReturn(Optional.empty());
    
    final var actualException = Assertions.assertThrows(
        DomainException.class,
        () -> useCase.execute(expectedId.getValue())
    );
    
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    
    verify(categoryGateway, times(1)).findById(eq(expectedId));
  }
  
  @Test
  public void givenAValidId_whenGatewayThrowsError_shouldReturnException() {
    final var expectedErrorMessage = "Gateway Error";
    final var expectedId = CategoryID.from("123");
    
    when(categoryGateway.findById(eq(expectedId)))
        .thenThrow(new IllegalStateException(expectedErrorMessage));
    
    final var actualException = Assertions.assertThrows(IllegalStateException.class,
                                                        () -> useCase.execute(expectedId.getValue()));
    
    Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    verify(categoryGateway, times(1)).findById(eq(expectedId));
  }
}
