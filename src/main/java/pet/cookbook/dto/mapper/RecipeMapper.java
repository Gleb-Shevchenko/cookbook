package pet.cookbook.dto.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pet.cookbook.dto.request.RecipeRequestDto;
import pet.cookbook.dto.response.CookbookResponseDto;
import pet.cookbook.dto.response.RecipeResponseDto;
import pet.cookbook.model.Cookbook;
import pet.cookbook.model.Recipe;
import pet.cookbook.service.CookbookService;
import pet.cookbook.service.RecipeService;

@AllArgsConstructor
@Component
public class RecipeMapper implements ResponseDtoMapper<RecipeResponseDto, Recipe>,
        RequestDtoMapper<RecipeRequestDto, Recipe> {
    private final CookbookService cookbookService;
    private final RecipeService recipeService;
    private final ResponseDtoMapper<CookbookResponseDto, Cookbook>
            cookbookResponseDtoMapper;

    @Override
    public Recipe mapToModel(RecipeRequestDto dto) {
        Recipe recipe = new Recipe();
        recipe.setParentRecipe(recipeService.findById(dto.getParentId()));
        recipe.setName(dto.getName());
        recipe.setDescription(dto.getDescription());
        recipe.setCookbook(cookbookService.findById(dto.getCookbookId()));
        return recipe;
    }

    @Override
    public RecipeResponseDto mapToDto(Recipe recipe) {
        RecipeResponseDto recipeResponseDto = new RecipeResponseDto();
        recipeResponseDto.setId(recipe.getId());
        recipeResponseDto.setParentRecipe(recipe.getParentRecipe());
        recipeResponseDto.setCookbookResponseDto(
                cookbookResponseDtoMapper.mapToDto(recipe.getCookbook()));
        recipeResponseDto.setPublicationDate(recipe.getPublicationDate());
        recipeResponseDto.setName(recipe.getName());
        recipeResponseDto.setDescription(recipe.getDescription());
        return recipeResponseDto;
    }
}
