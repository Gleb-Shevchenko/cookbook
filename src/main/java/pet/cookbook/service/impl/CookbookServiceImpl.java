package pet.cookbook.service.impl;

import java.util.*;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pet.cookbook.model.Cookbook;
import pet.cookbook.model.Recipe;
import pet.cookbook.repository.CookbookRepository;
import pet.cookbook.repository.RecipeRepository;
import pet.cookbook.service.CookbookService;

@AllArgsConstructor
@Service
public class CookbookServiceImpl implements CookbookService {
    private final RecipeRepository recipeRepository;
    private final CookbookRepository cookbookRepository;

    @Override
    public Cookbook save(Cookbook cookbook) {
        return cookbookRepository.save(cookbook);
    }

    @Override
    public Cookbook findById(Long id) {
        return cookbookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find cookbook by id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        cookbookRepository.deleteById(id);
    }

    @Override
    public List<Recipe> findAllRecipes(PageRequest pageRequest, Long id) {
        return recipeRepository.findAll()
                .stream()
                .filter(r -> Objects.equals(r.getCookbook().getId(), id))
                .toList();
    }
}
