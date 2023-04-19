package pet.cookbook.dto.response;

import java.util.List;
import lombok.Data;
import pet.cookbook.model.Recipe;

@Data
public class CookbookResponseDto {
    private Long id;
    private String name;
    private List<Recipe> recipes;
}
