package com.fullcyccle.admin.catalogo.application.category.update;

import com.fullcyccle.admin.catalogo.application.category.create.CreateCategoryCommand;
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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateCategoryUseCaseTest {
  @InjectMocks
  private DefaultUpdateCategoryUseCase useCase;
  
  @Mock
  private CategoryGateway categoryGateway;
  
  // toda vez antes de iniciar um teste esse método será chamado
  @BeforeEach
  void cleanUp() {
    Mockito.reset(categoryGateway);
  }
  
  @Test
  public void givenAValidCommand_whenCallsUpdateCategory_shouldReturnCategoryId() {
    final var aCategory = Category.newCategory("Film", null, true);
    
    
    final var expectedName = "Filmes";
    final var expectedDescription = "A Categoria mais assistida";
    final var expectedIsActive = true;
    final var expectedId = aCategory.getId();
    
    final var aCommand = UpdateCategoryCommand.with(
        expectedId.getValue(),
        expectedName,
        expectedDescription,
        expectedIsActive
    );
    
    // Quando chamar find by id passando o expectedId, vai retorna a categoria
    when(categoryGateway.findById(eq(expectedId)))
        .thenReturn(Optional.of(aCategory.clone()));
    
    // quando chamar o método update, passando qualquer valor, retorna o que veio na entrada
    when(categoryGateway.update(any()))
        .thenAnswer(returnsFirstArg());
    
    final var actualOutput = useCase.execute(aCommand).get(); // precisa desse get() pois o useCase retorna um Either
    
    Assertions.assertNotNull(actualOutput);
    Assertions.assertNotNull(actualOutput.id());
    
    Mockito.verify(categoryGateway, times(1)).findById(eq(expectedId));
    Mockito.verify(categoryGateway, times(1)).update(argThat(
        aUpdatedCategory -> Objects.equals(expectedName, aUpdatedCategory.getName())
            && Objects.equals(expectedDescription, aUpdatedCategory.getDescription())
            && Objects.equals(expectedIsActive, aUpdatedCategory.isActive())
            && Objects.equals(expectedId, aUpdatedCategory.getId())
            && Objects.equals(aCategory.getCreatedAt(), aUpdatedCategory.getCreatedAt())
            && aCategory.getUpdatedAt().isBefore(aUpdatedCategory.getUpdatedAt())
            && Objects.isNull(aUpdatedCategory.getDeletedAt())
    ));
  }
  
  @Test
  public void givenAnInvalidName_whenCallsUpdateCategory_thenShouldReturnDomainException() {
    final var aCategory = Category.newCategory("Film", null, true);
    
    final String expectedName = null;
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;
    final var expectedId = aCategory.getId();
    
    final var expectedErrorMessage = "'name' should not be null";
    final var expectedErrorCount = 1;
    
    final var aCommand = UpdateCategoryCommand.with(expectedId.getValue(),
                                                    expectedName, expectedDescription, expectedIsActive);
    
    // Quando chamar find by id passando o expectedId, vai retorna a categoria
    when(categoryGateway.findById(eq(expectedId)))
        .thenReturn(Optional.of(aCategory.clone()));
    
    final var notification = useCase.execute(aCommand).getLeft();
    
    Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());
    
    // verificar que isso realmente não foi chamado
    verify(categoryGateway, times(0)).update(any());
  }
  
  @Test
  public void givenAValidInactivateCommand_whenCallsUpdateCategory_shouldReturnInactiveCategoryId() {
    final var aCategory = Category.newCategory("Film", null, true);
    
    final var expectedName = "Filmes";
    final var expectedDescription = "A Categoria mais assistida";
    final var expectedIsActive = false;
    final var expectedId = aCategory.getId();
    
    final var aCommand = UpdateCategoryCommand.with(
        expectedId.getValue(),
        expectedName,
        expectedDescription,
        expectedIsActive
    );
    
    // Quando chamar find by id passando o expectedId, vai retorna a categoria
    when(categoryGateway.findById(eq(expectedId)))
        .thenReturn(Optional.of(aCategory.clone()));
    
    // quando chamar o método update, passando qualquer valor, retorna o que veio na entrada
    when(categoryGateway.update(any()))
        .thenAnswer(returnsFirstArg());
    
    Assertions.assertTrue(aCategory.isActive());
    Assertions.assertNull(aCategory.getDeletedAt());
    
    final var actualOutput = useCase.execute(aCommand).get(); // precisa desse get() pois o useCase retorna um Either
    
    Assertions.assertNotNull(actualOutput);
    Assertions.assertNotNull(actualOutput.id());
    
    Mockito.verify(categoryGateway, times(1)).findById(eq(expectedId));
    Mockito.verify(categoryGateway, times(1)).update(argThat(
        aUpdatedCategory -> Objects.equals(expectedName, aUpdatedCategory.getName())
            && Objects.equals(expectedDescription, aUpdatedCategory.getDescription())
            && Objects.equals(expectedIsActive, aUpdatedCategory.isActive())
            && Objects.equals(expectedId, aUpdatedCategory.getId())
            && Objects.equals(aCategory.getCreatedAt(), aUpdatedCategory.getCreatedAt())
            && aCategory.getUpdatedAt().isBefore(aUpdatedCategory.getUpdatedAt())
            && Objects.nonNull(aUpdatedCategory.getDeletedAt())
    ));
  }
  
  @Test
  public void givenAValidCommand_whenGatewayThrowsRandomException_shouldReturnAnException() {
    final var aCategory = Category.newCategory("Film", null, true);
    
    
    final var expectedName = "Filmes";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;
    final var expectedId = aCategory.getId();
    
    final var expectedErrorMessage = "Gateway Error";
    final var expectedErrorCount = 1;
    
    // Command é usado para dar semântica ao nome, poderia ser outro nome, como CreateCategoryInput
    final var aCommand = UpdateCategoryCommand.with(
        expectedId.getValue(), expectedName, expectedDescription, expectedIsActive);
    
    // Quando chamar find by id passando o expectedId, vai retorna a categoria
    when(categoryGateway.findById(eq(expectedId)))
        .thenReturn(Optional.of(aCategory.clone()));
    
    // Quando categoryGateway.create for chamado passando qualquer valor, eu vou retornar
    // o primeiro parâmetro que for passado
    when(categoryGateway.update(any()))
        .thenThrow(new IllegalStateException(expectedErrorMessage));
    
    final var notification = useCase.execute(aCommand).getLeft();
    
    Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());
    
    // verifica que foi chamado uma vez com os parâmetros certos
    Mockito.verify(categoryGateway, times(1)).update(argThat(
        aUpdatedCategory -> Objects.equals(expectedName, aUpdatedCategory.getName())
            && Objects.equals(expectedDescription, aUpdatedCategory.getDescription())
            && Objects.equals(expectedIsActive, aUpdatedCategory.isActive())
            && Objects.equals(expectedId, aUpdatedCategory.getId())
            && Objects.equals(aCategory.getCreatedAt(), aUpdatedCategory.getCreatedAt())
            && aCategory.getUpdatedAt().isBefore(aUpdatedCategory.getUpdatedAt())
            && Objects.isNull(aUpdatedCategory.getDeletedAt())
    ));
  }
  
  @Test
  public void givenACommandWithInvalidID_whenCallsUpdateCategory_thenShouldReturnDomainException() {
    final String expectedName = null;
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;
    final var expectedId = "123";
    
    final var expectedErrorMessage = "Category with ID 123 was not found";
    final var expectedErrorCount = 1;
    
    final var aCommand = UpdateCategoryCommand.with(expectedId,
                                                    expectedName, expectedDescription, expectedIsActive);
    
    // Quando chamar find by id passando um id que não existe, vai retornar vazio (nada)
    when(categoryGateway.findById(eq(CategoryID.from(expectedId))))
        .thenReturn(Optional.empty());
    
    
    final var actualException = Assertions.assertThrows(DomainException.class, () -> useCase.execute(aCommand));
    
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    
    verify(categoryGateway, times(1)).findById(eq(CategoryID.from(expectedId)));
    verify(categoryGateway, times(0)).update(any());
    
  }
}
