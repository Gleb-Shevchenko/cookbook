package pet.cookbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.cookbook.model.Cookbook;

@Repository
public interface CookbookRepository extends JpaRepository<Cookbook, Long> {
}
