package pet.cookbook.dto.response;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import pet.cookbook.model.Recipe;

@Data
public class RecipeResponseDto {
    private Long id;
    private Long parentId;
    private LocalDate publicationDate;
    private String name;
    private String description;
    private Long childRecipeId;
}
