package com.example.quanlynoiboapi.service;

import com.example.quanlynoiboapi.dto.NhanVienDuAnDTO;
import com.example.quanlynoiboapi.mapper.NhanVienDuAnMapper;
import com.example.quanlynoiboapi.model.NhanVienDuAn;
import com.example.quanlynoiboapi.reponsitory.NhanVienDuAnReponsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NhanVienDuAnService {

    private final NhanVienDuAnReponsitory nhanVienDuAnReponsitory;
    private final NhanVienDuAnMapper nhanVienDuAnMapper;

    public List<NhanVienDuAnDTO> getNhanVienDuAns() {
        return nhanVienDuAnMapper.toNhanVienDuAnDTOs(nhanVienDuAnReponsitory.findAll());
    }

    public void addNhanVienDuAn(NhanVienDuAnDTO nhanVienDuAnDTO) {
        nhanVienDuAnReponsitory.save(nhanVienDuAnMapper.toNhanVienDuAn(nhanVienDuAnDTO));
    }

    public void delete(Integer id) {
        nhanVienDuAnReponsitory.deleteById(id);
    }

    public NhanVienDuAnDTO getNhanVienDuAnById(Integer id) {
        Optional<NhanVienDuAn> nhanVienDuAn = nhanVienDuAnReponsitory.findById(id);
        if (nhanVienDuAn.isPresent()) {
            return nhanVienDuAnMapper.toNhanVienDuAnDTO(nhanVienDuAn.get());
        }

        return null;
    }

    public List<NhanVienDuAnDTO> getByNhanVien(Integer id) {
        return nhanVienDuAnMapper.toNhanVienDuAnDTOs(nhanVienDuAnReponsitory.findNhanVienDuAnByNhanVien_Id(id));
    }

    public List<NhanVienDuAnDTO> getByDuAn(Integer id) {
        return nhanVienDuAnMapper.toNhanVienDuAnDTOs(nhanVienDuAnReponsitory.findNhanVienDuAnByDuAn_Id(id));
    }

    public List<NhanVienDuAnDTO> getDuAnByNhanVien(Integer id) {
        return nhanVienDuAnMapper.toNhanVienDuAnDTOs(nhanVienDuAnReponsitory.findNhanVienDuAnByNhanVien_Id(id));
    }

}
