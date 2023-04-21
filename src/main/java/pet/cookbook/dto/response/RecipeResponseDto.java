package pet.cookbook.dto.response;

import java.time.LocalDateTime;
import lombok.Data;
import pet.cookbook.model.Recipe;

@Data
public class RecipeResponseDto {
    private Long id;
    private Recipe parentRecipe;
    private CookbookResponseDto cookbookResponseDto;
    private LocalDateTime publicationDate;
    private String name;
    private String description;
}
