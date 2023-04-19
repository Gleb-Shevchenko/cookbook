package pet.cookbook.dto.response;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class RecipeResponseDto {
    private Long id;
    private Long parentId;
    private Long cookbookId;
    private String cookbookName;
    private LocalDateTime publicationDate;
    private String name;
    private String description;
}
