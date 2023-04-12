package pet.cookbook.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;
import pet.cookbook.dto.mapper.ResponseDtoMapper;
import pet.cookbook.dto.response.CookbookResponseDto;
import pet.cookbook.dto.response.RecipeResponseDto;
import pet.cookbook.model.Cookbook;
import pet.cookbook.model.Recipe;
import pet.cookbook.service.CookbookService;

import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@RestController
@RequestMapping("/cookbooks")
public class CookbookController {
    private final CookbookService cookbookService;
    private final ResponseDtoMapper<CookbookResponseDto, Cookbook>
            cookbookResponseDtoMapper;
    private final ResponseDtoMapper<RecipeResponseDto, Recipe>
            recipeResponseDtoMapper;

    @PostMapping
    public CookbookResponseDto save(@RequestParam String name) {
        Cookbook cookbook = new Cookbook();
        cookbook.setName(name);
        cookbookService.save(cookbook);
        return cookbookResponseDtoMapper.mapToDto(cookbook);
    }

    @GetMapping("/{id}")
    public CookbookResponseDto findById(@PathVariable Long id) {
        Cookbook cookbook = cookbookService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find cookbook by id: " + id));
        return cookbookResponseDtoMapper.mapToDto(cookbook);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        cookbookService.deleteById(id);
    }

    @GetMapping("/allRecipes/{id}")
    public List<RecipeResponseDto> findAllRecipes(@PathVariable Long id) {
        return cookbookService.findAllRecipes(id).stream()
                .map(recipeResponseDtoMapper::mapToDto)
                .toList();
    }
}
