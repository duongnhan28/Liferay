package com.example.quanlynoiboapi.mapper;

import com.example.quanlynoiboapi.dto.VaiTroDTO;
import com.example.quanlynoiboapi.model.VaiTro;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VaitroMapper {
    VaiTro toVaiTro(VaiTroDTO vaiTroDTO);

    VaiTroDTO toVaiTroDTO(VaiTro vaiTro);

    List<VaiTroDTO> toVaiTroDTOs(List<VaiTro> vaiTros);
}
