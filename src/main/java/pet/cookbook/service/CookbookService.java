package pet.cookbook.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import pet.cookbook.model.Cookbook;
import pet.cookbook.model.Recipe;

public interface CookbookService {
    Cookbook save(Cookbook cookbook);

    Cookbook findById(Long id);

    void deleteById(Long id);

    List<Recipe> findAllRecipes(PageRequest pageRequest, Long id);
}
