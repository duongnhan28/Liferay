package com.example.quanlynoiboapi.mapper;

import com.example.quanlynoiboapi.dto.NhanVienDuAnDTO;
import com.example.quanlynoiboapi.model.NhanVienDuAn;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NhanVienDuAnMapper {
    NhanVienDuAn toNhanVienDuAn(NhanVienDuAnDTO nhanVienDuAnDTO);

    NhanVienDuAnDTO toNhanVienDuAnDTO(NhanVienDuAn nhanVienDuAn);

    List<NhanVienDuAnDTO> toNhanVienDuAnDTOs(List<NhanVienDuAn> nhanVienDuAns);
}
