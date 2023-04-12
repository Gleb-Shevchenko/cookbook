package pet.cookbook.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "cookbooks")
public class Cookbook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    @JoinTable(name = "cookbooks_recipes",
            joinColumns = @JoinColumn(name = "cookbook_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private List<Recipe> recipes;

    public List<Recipe> getAllRecipes() {
        List<Recipe> allRecipes = new ArrayList<>();
        List<Recipe> rootRecipes = recipes.stream()
                .filter(recipe -> recipe.getParentRecipeId() == null)
                .toList();
        for (Recipe recipe : rootRecipes) {
            addRecipeAndChildren(recipe, allRecipes);
        }
        return allRecipes;
    }

    private void addRecipeAndChildren(Recipe recipe, List<Recipe> allRecipes) {
        allRecipes.add(recipe);
        List<Recipe> childRecipes = recipes.stream()
                .filter(childRecipe -> childRecipe.getParentRecipeId() != null
                        && childRecipe.getParentRecipeId().equals(recipe.getId()))
                .toList();
        for (Recipe child : childRecipes) {
            addRecipeAndChildren(child, allRecipes);
        }
    }
}
