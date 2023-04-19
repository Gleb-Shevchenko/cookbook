package pet.cookbook.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pet.cookbook.model.Recipe;
import pet.cookbook.repository.RecipeRepository;
import pet.cookbook.service.RecipeService;

@AllArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    @Override
    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Optional<Recipe> findById(Long id) {
        return recipeRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public void findVersionsOfRecipe(PageRequest pageRequest, List<Recipe> recipes, Recipe recipe) {
        recipes.add(recipe);
        Long parentId = recipe.getParentRecipeId();
        if (parentId != null) {
            Recipe parentRecipe = recipeRepository.findById(parentId)
                    .orElseThrow(() -> new NoSuchElementException("Can't find recipe by id: " + parentId));
            findVersionsOfRecipe(pageRequest, recipes, parentRecipe);
        }
    }
}
