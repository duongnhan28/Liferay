package com.example.quanlynoiboapi.service;

import com.example.quanlynoiboapi.dto.NhanVienDTO;
import com.example.quanlynoiboapi.mapper.NhanVienMapper;
import com.example.quanlynoiboapi.model.NhanVien;
import com.example.quanlynoiboapi.reponsitory.NhanVienReponsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NhanVienService {
    private final NhanVienReponsitory nhanVienReponsitory;
    private final NhanVienMapper nhanVienMapper;

    public List<NhanVienDTO> getAllNhanVien() {
        return nhanVienMapper.toNhanVienDTOs(nhanVienReponsitory.findAll());
    }

    public NhanVienDTO getNhanVienId(Integer id) {
        Optional<NhanVien> nhanVien = nhanVienReponsitory.findById(id);

        if (nhanVien.isPresent()) {
            return nhanVienMapper.toNhanVienDTO(nhanVien.get());
        }
        return null;
    }

    public void addNhanVien(NhanVienDTO nhanVienDTO) {
        nhanVienReponsitory.save(nhanVienMapper.toNhanVien(nhanVienDTO));
    }

    public void updateNhanVien(NhanVienDTO nhanVienDTO) {
        nhanVienReponsitory.save(nhanVienMapper.toNhanVien(nhanVienDTO));
    }

    public void deleteNhanVien(Integer id) {
        nhanVienReponsitory.deleteById(id);
    }

    public NhanVienDTO getNhanVienByUserId(long id) {
        return nhanVienMapper.toNhanVienDTO(nhanVienReponsitory.findByUserId(id));
    }


}
