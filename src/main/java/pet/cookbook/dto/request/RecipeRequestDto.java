package pet.cookbook.dto.request;

import java.time.LocalDate;
import lombok.Data;

@Data
public class RecipeRequestDto {
    private Long parentId;
    private String name;
    private String description;
}
