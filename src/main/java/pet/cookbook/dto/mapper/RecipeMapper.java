package pet.cookbook.dto.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pet.cookbook.dto.request.RecipeRequestDto;
import pet.cookbook.dto.response.RecipeResponseDto;
import pet.cookbook.model.Recipe;
import pet.cookbook.service.RecipeService;

@AllArgsConstructor
@Component
public class RecipeMapper implements ResponseDtoMapper<RecipeResponseDto, Recipe>,
        RequestDtoMapper<RecipeRequestDto, Recipe> {
    private final RecipeService recipeService;

    @Override
    public Recipe mapToModel(RecipeRequestDto dto) {
        Recipe recipe = new Recipe();
        recipe.setParentRecipeId(dto.getParentId());
        recipe.setName(dto.getName());
        recipe.setDescription(dto.getDescription());
        return recipe;
    }

    @Override
    public RecipeResponseDto mapToDto(Recipe recipe) {
        RecipeResponseDto recipeResponseDto = new RecipeResponseDto();
        recipeResponseDto.setId(recipe.getId());
        recipeResponseDto.setParentId(recipe.getParentRecipeId());
        recipeResponseDto.setPublicationDate(recipe.getPublicationDate());
        recipeResponseDto.setName(recipe.getName());
        recipeResponseDto.setDescription(recipe.getDescription());
        recipeResponseDto.setChildRecipeId(recipe.getChildRecipeId());
        return recipeResponseDto;
    }
}
