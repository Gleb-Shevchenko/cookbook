package pet.cookbook.service;

import pet.cookbook.model.Cookbook;
import pet.cookbook.model.Recipe;

import java.util.List;
import java.util.Optional;

public interface CookbookService {
    Cookbook save(Cookbook cookbook);

    Optional<Cookbook> findById(Long id);

    void deleteById(Long id);

    List<Recipe> findAllRecipes(Long id);
}
