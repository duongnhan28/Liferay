package com.example.quanlynoiboapi.mapper;

import com.example.quanlynoiboapi.dto.ChiPhiPhatSinhDTO;
import com.example.quanlynoiboapi.model.ChiPhiPhatSinh;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChiPhiPhatSinhMapper {

    ChiPhiPhatSinh toCPPS(ChiPhiPhatSinhDTO chiPhiPhatSinhDTO);

    ChiPhiPhatSinhDTO toCPPSDTO(ChiPhiPhatSinh chiPhiPhatSinh);

    List<ChiPhiPhatSinhDTO> toCPPSDTOs(List<ChiPhiPhatSinh> chiPhiPhatSinhs);
}
