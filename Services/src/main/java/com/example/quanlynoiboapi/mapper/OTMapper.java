package com.example.quanlynoiboapi.mapper;


import com.example.quanlynoiboapi.dto.OTDTO;
import com.example.quanlynoiboapi.model.OT;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OTMapper {
    OT toOT(OTDTO otdto);

    OTDTO toOTDTO(OT ot);

    List<OTDTO> toOTDTOs(List<OT> ots);
}
