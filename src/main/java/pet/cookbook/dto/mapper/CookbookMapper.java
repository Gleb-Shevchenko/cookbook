package pet.cookbook.dto.mapper;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import pet.cookbook.dto.request.CookbookRequestDto;
import pet.cookbook.dto.response.CookbookResponseDto;
import pet.cookbook.model.Cookbook;
import pet.cookbook.service.CookbookService;

@AllArgsConstructor
@Component
@Primary
public class CookbookMapper implements ResponseDtoMapper<CookbookResponseDto, Cookbook>,
        RequestDtoMapper<CookbookRequestDto, Cookbook> {
    private final CookbookService cookbookService;

    @Override
    public Cookbook mapToModel(CookbookRequestDto dto) {
        Cookbook cookbook = new Cookbook();
        cookbook.setName(dto.getName());
        return cookbook;
    }

    @Override
    public CookbookResponseDto mapToDto(Cookbook cookbook) {
        CookbookResponseDto cookbookResponseDto = new CookbookResponseDto();
        cookbookResponseDto.setId(cookbook.getId());
        cookbookResponseDto.setName(cookbookResponseDto.getName());
        cookbookResponseDto.setRecipes(cookbook.getRecipes());
        return cookbookResponseDto;
    }
}
