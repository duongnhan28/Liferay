package com.example.quanlynoiboapi.mapper;

import com.example.quanlynoiboapi.dto.NhanVienDTO;
import com.example.quanlynoiboapi.model.NhanVien;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NhanVienMapper {

    NhanVien toNhanVien(NhanVienDTO nhanVienDTO);

    NhanVienDTO toNhanVienDTO(NhanVien nhanVien);

    List<NhanVienDTO> toNhanVienDTOs(List<NhanVien> nhanViens);
}
