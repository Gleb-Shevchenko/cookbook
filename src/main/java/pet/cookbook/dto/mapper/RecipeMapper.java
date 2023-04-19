package pet.cookbook.dto.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pet.cookbook.dto.request.CookbookRequestWithIdDto;
import pet.cookbook.dto.request.RecipeRequestDto;
import pet.cookbook.dto.response.RecipeResponseDto;
import pet.cookbook.model.Cookbook;
import pet.cookbook.model.Recipe;
import pet.cookbook.service.CookbookService;

@AllArgsConstructor
@Component
public class RecipeMapper implements ResponseDtoMapper<RecipeResponseDto, Recipe>,
        RequestDtoMapper<RecipeRequestDto, Recipe> {
    private final CookbookService cookbookService;
    private final RequestDtoMapper<CookbookRequestWithIdDto, Cookbook>
            cookbookRequestDtoMapper;

    @Override
    public Recipe mapToModel(RecipeRequestDto dto) {
        Recipe recipe = new Recipe();
        recipe.setParentRecipeId(dto.getParentId());
        recipe.setCookbook(cookbookService.findById(
                cookbookRequestDtoMapper.mapToModel(dto.getCookbook()).getId()).get());
        recipe.setName(dto.getName());
        recipe.setDescription(dto.getDescription());
        return recipe;
    }

    @Override
    public RecipeResponseDto mapToDto(Recipe recipe) {
        RecipeResponseDto recipeResponseDto = new RecipeResponseDto();
        recipeResponseDto.setId(recipe.getId());
        recipeResponseDto.setParentId(recipe.getParentRecipeId());
        recipeResponseDto.setCookbookId(recipe.getCookbook().getId());
        recipeResponseDto.setCookbookName(recipe.getCookbook().getName());
        recipeResponseDto.setPublicationDate(recipe.getPublicationDate());
        recipeResponseDto.setName(recipe.getName());
        recipeResponseDto.setDescription(recipe.getDescription());
        return recipeResponseDto;
    }
}
