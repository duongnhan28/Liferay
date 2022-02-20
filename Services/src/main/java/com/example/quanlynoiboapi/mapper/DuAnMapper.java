package com.example.quanlynoiboapi.mapper;

import com.example.quanlynoiboapi.dto.DuAnDTO;
import com.example.quanlynoiboapi.model.DuAn;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DuAnMapper {

    DuAn toDuAn(DuAnDTO duAnDTO);

    DuAnDTO toDuAnDTO(DuAn duAn);

    List<DuAnDTO> toDuAnDTOs(List<DuAn> duAns);
}
