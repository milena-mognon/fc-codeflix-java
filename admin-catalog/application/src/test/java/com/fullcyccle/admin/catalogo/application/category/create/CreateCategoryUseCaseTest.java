package com.fullcyccle.admin.catalogo.application.category.create;

import com.fullcyccle.admin.catalogo.domain.category.CategoryGateway;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;


public class CreateCategoryUseCaseTest {
  // Teste do caminho feliz
  
  
  // Teste passando uma propriedade invalida
  
  // Teste criando uma categoria inativa
  
  // Teste simulando um erro genérico vindo do gateway
  
  public void givenAValidCommand_whenCallsCreateCategory_shouldReturnCategoryId() {
    final var expectedName = "Filmes";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;
    
    // Command é usado para dar semântica ao nome, poderia ser outro nome, como CreateCategoryInput
    final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);
    
    // mockito irá criar uma implementação dessa interface com as informações mocadas
    final CategoryGateway categoryGateway = Mockito.mock(CategoryGateway.class);
    
    // Quando categoryGateway.create for chamado passando qualquer valor, eu vou retornar
    // o primeiro parâmetro que for passado
    Mockito.when(categoryGateway.create(Mockito.any()))
        .thenAnswer(returnsFirstArg());
    
    final var useCase = new CreateCategoryUseCase(categoryGateway);
    
    final var actualOutput = useCase.execute(aCommand);
    
    Assertions.assertNotNull(actualOutput);
    Assertions.assertNotNull(actualOutput.getId());
    
    Mockito.verify(categoryGateway, Mockito.times(1)).create(
        Mockito.argThat(aCategory -> {
                          return Objects.equals(expectedName, aCategory.getName())
                              && Objects.equals(expectedDescription, aCategory.getDescription())
                              && Objects.equals(expectedIsActive, aCategory.isActive())
                              && Objects.nonNull(aCategory.getId())
                              && Objects.nonNull(aCategory.getCreatedAt())
                              && Objects.nonNull(aCategory.getUpdatedAt())
                              && Objects.isNull(aCategory.getDeletedAt());
                        }
        ));
  }
}
