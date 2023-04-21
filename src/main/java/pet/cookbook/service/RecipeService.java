package pet.cookbook.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import pet.cookbook.model.Recipe;

public interface RecipeService {
    Recipe save(Recipe recipe);

    Recipe findById(Long id);

    void deleteById(Long id);

    List<Recipe> findVersionsOfRecipe(PageRequest pageRequest, Long id);
}
