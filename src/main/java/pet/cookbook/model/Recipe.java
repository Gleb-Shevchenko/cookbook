package pet.cookbook.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long parentRecipeId;
    @ManyToOne
    @JoinColumn(name = "cookbook_id")
    private Cookbook cookbook;
    private LocalDateTime publicationDate;
    private String name;
    private String description;
}
