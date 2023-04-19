package pet.cookbook.dto.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pet.cookbook.dto.request.CookbookRequestWithIdDto;
import pet.cookbook.dto.response.CookbookResponseDto;
import pet.cookbook.model.Cookbook;
import pet.cookbook.service.CookbookService;

@AllArgsConstructor
@Component
public class CookbookWithIdMapper implements ResponseDtoMapper<CookbookResponseDto, Cookbook>,
        RequestDtoMapper<CookbookRequestWithIdDto, Cookbook> {
    private final CookbookService cookbookService;

    @Override
    public Cookbook mapToModel(CookbookRequestWithIdDto dto) {
        Cookbook cookbook = new Cookbook();
        cookbook.setId(dto.getId());
        cookbook.setName(dto.getName());
        return cookbook;
    }

    @Override
    public CookbookResponseDto mapToDto(Cookbook cookbook) {
        CookbookResponseDto cookbookResponseDto = new CookbookResponseDto();
        cookbookResponseDto.setId(cookbook.getId());
        cookbookResponseDto.setName(cookbook.getName());
        cookbookResponseDto.setRecipes(cookbook.getRecipes());
        return cookbookResponseDto;
    }
}

