package pet.cookbook.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import pet.cookbook.model.Recipe;

public interface RecipeService {
    Recipe save(Recipe recipe);

    Optional<Recipe> findById(Long id);

    void deleteById(Long id);

    void findVersionsOfRecipe(PageRequest pageRequest, List<Recipe> recipes, Recipe recipe);
}
