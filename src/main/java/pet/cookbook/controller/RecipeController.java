package pet.cookbook.controller;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
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
        recipe.setPublicationDate(LocalDate.now());
        recipeService.save(recipe);
        return recipeResponseDtoMapper.mapToDto(recipe);
    }

    @GetMapping("/{id}")
    public RecipeResponseDto findById(@PathVariable Long id) {
        Recipe recipe = recipeService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find recipe by id: " + id));
        return recipeResponseDtoMapper.mapToDto(recipe);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        recipeService.deleteById(id);
    }
}
