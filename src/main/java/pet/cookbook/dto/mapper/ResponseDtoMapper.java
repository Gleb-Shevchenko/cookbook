package mate.academy.springboot.datajpa.dto.mapper;

public interface ResponseDtoMapper<D, T> {
    D mapToDto(T t);
}
