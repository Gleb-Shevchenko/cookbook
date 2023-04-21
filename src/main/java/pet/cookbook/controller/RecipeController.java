package pet.cookbook.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pet.cookbook.dto.mapper.RequestDtoMapper;
import pet.cookbook.dto.mapper.ResponseDtoMapper;
import pet.cookbook.dto.request.RecipeRequestDto;
import pet.cookbook.dto.response.RecipeResponseDto;
import pet.cookbook.model.Recipe;
import pet.cookbook.service.RecipeService;

@AllArgsConstructor
@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;
    private final ResponseDtoMapper<RecipeResponseDto, Recipe>
            recipeResponseDtoMapper;
    private final RequestDtoMapper<RecipeRequestDto, Recipe>
            recipeRequestDtoMapper;

    @PostMapping
    public RecipeResponseDto save(@RequestBody RecipeRequestDto recipeRequestDto) {
        Recipe recipe = recipeRequestDtoMapper.mapToModel(recipeRequestDto);
        recipeService.save(recipe);
        return recipeResponseDtoMapper.mapToDto(recipe);
    }

    @GetMapping("/{id}")
    public RecipeResponseDto findById(@PathVariable Long id) {
        Recipe recipe = recipeService.findById(id);
        return recipeResponseDtoMapper.mapToDto(recipe);
    }

    @PutMapping("/{id}")
    public RecipeResponseDto update(@PathVariable Long id,
                                          @RequestBody RecipeRequestDto recipeRequestDto) {
        Recipe recipe = recipeService.findById(id);
        recipe.setName(recipeRequestDto.getName());
        recipe.setDescription(recipe.getDescription());
        recipeService.save(recipe);
        return recipeResponseDtoMapper.mapToDto(recipe);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        recipeService.deleteById(id);
    }

    @GetMapping("/{id}/versions")
    public List<RecipeResponseDto> findVersionsOfRecipe(@PathVariable Long id,
                                                        @RequestParam (defaultValue = "5") Integer count,
                                                        @RequestParam (defaultValue = "0") Integer page,
                                                        @RequestParam (defaultValue = "name") String sortBy) {
        Sort sort = Sort.by(sortBy);
        PageRequest pageRequest = PageRequest.of(count, page, sort);
        return recipeService.findVersionsOfRecipe(pageRequest, id)
                .stream()
                .map(recipeResponseDtoMapper::mapToDto)
                .toList();
    }
}
