package pet.cookbook.service;

import pet.cookbook.model.Recipe;

import java.util.Optional;

public interface RecipeService {
    Recipe save(Recipe recipe);

    Optional<Recipe> findById(Long id);

    void deleteById(Long id);
}
