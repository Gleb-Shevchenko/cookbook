package mate.academy.springboot.datajpa.dto.mapper;

public interface RequestDtoMapper<D, T> {
    T mapToModel(D dto);
}
