package pet.cookbook.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
        recipe.setPublicationDate(LocalDateTime.now());
        recipeService.save(recipe);
        return recipeResponseDtoMapper.mapToDto(recipe);
    }

    @GetMapping("/{id}")
    public RecipeResponseDto findById(@PathVariable Long id) {
        Recipe recipe = recipeService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find recipe by id: " + id));
        return recipeResponseDtoMapper.mapToDto(recipe);
    }

    @PatchMapping("/{id}")
    public RecipeResponseDto updateRecipe(@PathVariable Long id,
                                          @RequestParam String name,
                                          @RequestParam String description) {
        Recipe recipe = recipeService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find recipe by id: " + id));
        recipe.setName(name);
        recipe.setDescription(description);
        recipeService.save(recipe);
        return recipeResponseDtoMapper.mapToDto(recipe);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        recipeService.deleteById(id);
    }

    @GetMapping("/versions/{id}")
    public List<RecipeResponseDto> findVersionsOfRecipe(@PathVariable Long id,
                                                        @RequestParam (defaultValue = "5") Integer count,
                                                        @RequestParam (defaultValue = "0") Integer page,
                                                        @RequestParam (defaultValue = "id") String sortBy) {
        Sort sort = Sort.by(sortBy);
        PageRequest pageRequest = PageRequest.of(count, page, sort);
        Recipe recipe = recipeService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find recipe by id: " + id));
        List<Recipe> recipes = new ArrayList<>();
        recipeService.findVersionsOfRecipe(pageRequest, recipes, recipe);
        return recipes.stream()
                .map(recipeResponseDtoMapper::mapToDto)
                .toList();
    }
}
