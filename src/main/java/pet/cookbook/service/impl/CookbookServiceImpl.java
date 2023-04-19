package pet.cookbook.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pet.cookbook.model.Cookbook;
import pet.cookbook.model.Recipe;
import pet.cookbook.repository.CookbookRepository;
import pet.cookbook.service.CookbookService;

@AllArgsConstructor
@Service
public class CookbookServiceImpl implements CookbookService {
    private final CookbookRepository cookbookRepository;

    @Override
    public Cookbook save(Cookbook cookbook) {
        return cookbookRepository.save(cookbook);
    }

    @Override
    public Optional<Cookbook> findById(Long id) {
        return cookbookRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        cookbookRepository.deleteById(id);
    }

    @Override
    public List<Recipe> findAllRecipes(PageRequest pageRequest, Long id) {
        Cookbook cookbook = cookbookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find cookbook by id: " + id));
        return cookbook.getAllRecipes();
    }
}
