package pet.cookbook.dto.response;

import lombok.Data;
import pet.cookbook.model.Recipe;
import java.util.List;

@Data
public class CookbookResponseDto {
    private Long id;
    private String name;
    private List<Recipe> recipes;
}
