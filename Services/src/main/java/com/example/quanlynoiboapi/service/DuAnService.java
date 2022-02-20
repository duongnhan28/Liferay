package com.example.quanlynoiboapi.service;

import com.example.quanlynoiboapi.dto.OTDTO;
import com.example.quanlynoiboapi.reponsitory.DuAnRepository;
import com.example.quanlynoiboapi.dto.DuAnDTO;
import com.example.quanlynoiboapi.mapper.DuAnMapper;
import com.example.quanlynoiboapi.model.DuAn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DuAnService {

    private final DuAnRepository duAnRepository;
    private final DuAnMapper duAnMapper;

    public List<DuAnDTO> getDuAns() {

        return duAnMapper.toDuAnDTOs(duAnRepository.findAll());
    }

    public List<DuAnDTO> getByNhanVien(Integer id) {

        return duAnMapper.toDuAnDTOs(duAnRepository.findDuAnById(id));
    }

    public void addDuAn(DuAnDTO duAnDTO) {

        duAnRepository.save(duAnMapper.toDuAn(duAnDTO));
    }

    public void delete(Integer id) {

        duAnRepository.deleteById(id);
    }

    public DuAnDTO getDuAnById(Integer id) {

        Optional<DuAn> duAn = duAnRepository.findById(id);

        if (duAn.isPresent()) {
            return duAnMapper.toDuAnDTO(duAn.get());
        }

        return null;
    }

    public List<DuAnDTO> findDuAnByNhanVien(long userId) {
        return duAnMapper.toDuAnDTOs(duAnRepository.findDuAnByNhanVienDuAns_NhanVien_UserId(userId));
    }
}
