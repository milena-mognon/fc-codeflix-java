package com.fullcyccle.admin.catalogo.application.category.create;

import com.fullcyccle.admin.catalogo.domain.category.CategoryGateway;
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

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CreateCategoryUseCaseTest {
  /**
   * Essa parte é usada para não ter a necessidade de colocar em todos os testes a criação do mock e useCase
   * Substitui isso: final var useCase = new DefaultCreateCategoryUseCase(categoryGateway);
   */
  @InjectMocks
  private DefaultCreateCategoryUseCase useCase;
  
  /**
   * Mockito irá criar uma implementação dessa interface com as informações mocadas
   * Substitui isso: final CategoryGateway categoryGateway = mock(CategoryGateway.class);
   */
  @Mock
  private CategoryGateway categoryGateway;
  
  // toda vez antes de iniciar um teste esse método será chamado
  @BeforeEach
  void cleanUp() {
    Mockito.reset(categoryGateway);
  }
  
  @Test
  public void givenAValidCommand_whenCallsCreateCategory_shouldReturnCategoryId() {
    final var expectedName = "Filmes";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;
    
    // Command é usado para dar semântica ao nome, poderia ser outro nome, como CreateCategoryInput
    final var aCommand = CreateCategoryCommand.with(
        expectedName, expectedDescription, expectedIsActive);
    
    
    // Quando categoryGateway.create for chamado passando qualquer valor, eu vou retornar
    // o primeiro parâmetro que for passado
    when(categoryGateway.create(any()))
        .thenAnswer(returnsFirstArg());
    
    final var actualOutput = useCase.execute(aCommand).get(); // Pega o valor retornado
    
    Assertions.assertNotNull(actualOutput);
    Assertions.assertNotNull(actualOutput.id());
    
    // Mockito está importado estaticamente
    verify(categoryGateway, times(1)).create(
        argThat(aCategory ->
                    Objects.equals(expectedName, aCategory.getName())
                        && Objects.equals(expectedDescription, aCategory.getDescription())
                        && Objects.equals(expectedIsActive, aCategory.isActive())
                        && Objects.nonNull(aCategory.getId())
                        && Objects.nonNull(aCategory.getCreatedAt())
                        && Objects.nonNull(aCategory.getUpdatedAt())
                        && Objects.isNull(aCategory.getDeletedAt())
        
        ));
  }
  
  @Test
  public void givenAnInvalidName_whenCallsCreateCategory_thenShouldReturnDomainException() {
    final String expectedName = null;
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;
    final var expectedErrorMessage = "'name' should not be null";
    final var expectedErrorCount = 1;
    
    final var aCommand = CreateCategoryCommand.with(
        expectedName, expectedDescription, expectedIsActive);
    
    final var notification = useCase.execute(aCommand).getLeft();
    
    Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());
    
    // verificar que isso realmente não foi chamado
    verify(categoryGateway, times(0)).create(any());
  }
  
  @Test
  public void givenAValidCommandWithInactiveCategory_whenCallsCreateCategory_shouldReturnInactiveCategoryId() {
    final var expectedName = "Filmes";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = false;
    
    // Command é usado para dar semântica ao nome, poderia ser outro nome, como CreateCategoryInput
    final var aCommand = CreateCategoryCommand.with(
        expectedName, expectedDescription, expectedIsActive);
    
    
    // Quando categoryGateway.create for chamado passando qualquer valor, eu vou retornar
    // o primeiro parâmetro que for passado
    when(categoryGateway.create(any()))
        .thenAnswer(returnsFirstArg());
    
    final var actualOutput = useCase.execute(aCommand).get();
    
    Assertions.assertNotNull(actualOutput);
    Assertions.assertNotNull(actualOutput.id());
    
    // Mockito está importado estaticamente
    verify(categoryGateway, times(1)).create(
        argThat(aCategory ->
                    Objects.equals(expectedName, aCategory.getName())
                        && Objects.equals(expectedDescription, aCategory.getDescription())
                        && Objects.equals(expectedIsActive, aCategory.isActive())
                        && Objects.nonNull(aCategory.getId())
                        && Objects.nonNull(aCategory.getCreatedAt())
                        && Objects.nonNull(aCategory.getUpdatedAt())
                        && Objects.nonNull(aCategory.getDeletedAt())
        
        ));
  }
  
  @Test
  public void givenAValidCommand_whenGatewayThrowsRandomException_shouldReturnAnException() {
    final var expectedName = "Filmes";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;
    final var expectedErrorMessage = "Gateway Error";
    final var expectedErrorCount = 1;
    
    // Command é usado para dar semântica ao nome, poderia ser outro nome, como CreateCategoryInput
    final var aCommand = CreateCategoryCommand.with(
        expectedName, expectedDescription, expectedIsActive);
    
    
    // Quando categoryGateway.create for chamado passando qualquer valor, eu vou retornar
    // o primeiro parâmetro que for passado
    when(categoryGateway.create(any()))
        .thenThrow(new IllegalStateException(expectedErrorMessage));
    
    final var notification = useCase.execute(aCommand).getLeft();
    
    Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());
    
    // verifica que foi chamado uma vez com os parâmetros certos
    verify(categoryGateway, times(1)).create(argThat(aCategory ->
                                                         Objects.equals(expectedName, aCategory.getName())
                                                             && Objects.equals(expectedDescription,
                                                                               aCategory.getDescription())
                                                             && Objects.equals(expectedIsActive, aCategory.isActive())
                                                             && Objects.nonNull(aCategory.getId())
                                                             && Objects.nonNull(aCategory.getCreatedAt())
                                                             && Objects.nonNull(aCategory.getUpdatedAt())
                                                             && Objects.isNull(aCategory.getDeletedAt())
    
    ));
  }
}
