package pet.cookbook.controller;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pet.cookbook.dto.mapper.RequestDtoMapper;
import pet.cookbook.dto.mapper.ResponseDtoMapper;
import pet.cookbook.dto.request.CookbookRequestDto;
import pet.cookbook.dto.response.CookbookResponseDto;
import pet.cookbook.dto.response.RecipeResponseDto;
import pet.cookbook.model.Cookbook;
import pet.cookbook.model.Recipe;
import pet.cookbook.service.CookbookService;

@AllArgsConstructor
@RestController
@RequestMapping("/cookbooks")
public class CookbookController {
    private final CookbookService cookbookService;
    private final ResponseDtoMapper<CookbookResponseDto, Cookbook>
            cookbookResponseDtoMapper;
    private final RequestDtoMapper<CookbookRequestDto, Cookbook>
            cookbookRequestDtoMapper;
    private final ResponseDtoMapper<RecipeResponseDto, Recipe>
            recipeResponseDtoMapper;

    @PostMapping
    public CookbookResponseDto save(@RequestBody CookbookRequestDto cookbookRequestDto) {
        Cookbook cookbook = cookbookRequestDtoMapper.mapToModel(cookbookRequestDto);
        cookbookService.save(cookbook);
        return cookbookResponseDtoMapper.mapToDto(cookbook);
    }

    @GetMapping("/{id}")
    public CookbookResponseDto findById(@PathVariable Long id) {
        Cookbook cookbook = cookbookService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find cookbook by id: " + id));
        return cookbookResponseDtoMapper.mapToDto(cookbook);
    }

    @PatchMapping("/{id}")
    public CookbookResponseDto changeNameOfCookbook(@PathVariable Long id,
                                                    @RequestParam String name) {
        Cookbook cookbook = cookbookService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find cookbook by id: " + id));
        cookbook.setName(name);
        cookbookService.save(cookbook);
        return cookbookResponseDtoMapper.mapToDto(cookbook);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        cookbookService.deleteById(id);
    }

    @GetMapping("/allRecipes/{id}")
    public List<RecipeResponseDto> findAllRecipes(@PathVariable Long id,
                                                  @RequestParam (defaultValue = "5") Integer count,
                                                  @RequestParam (defaultValue = "0") Integer page,
                                                  @RequestParam (defaultValue = "id") String sortBy) {
        Sort sort = Sort.by(sortBy);
        PageRequest pageRequest = PageRequest.of(count, page, sort);
        return cookbookService.findAllRecipes(pageRequest, id)
                .stream()
                .map(recipeResponseDtoMapper::mapToDto)
                .toList();
    }
}
