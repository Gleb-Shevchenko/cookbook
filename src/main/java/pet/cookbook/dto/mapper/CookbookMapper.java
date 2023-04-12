package pet.cookbook.dto.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pet.cookbook.dto.response.CookbookResponseDto;
import pet.cookbook.model.Cookbook;
import pet.cookbook.service.CookbookService;

@AllArgsConstructor
@Component
public class CookbookMapper implements ResponseDtoMapper<CookbookResponseDto, Cookbook> {
    private final CookbookService cookbookService;

    @Override
    public CookbookResponseDto mapToDto(Cookbook cookbook) {
        CookbookResponseDto cookbookResponseDto = new CookbookResponseDto();
        cookbookResponseDto.setId(cookbook.getId());
        cookbookResponseDto.setName(cookbookResponseDto.getName());
        cookbookResponseDto.setRecipes(cookbook.getRecipes());
        return cookbookResponseDto;
    }
}
