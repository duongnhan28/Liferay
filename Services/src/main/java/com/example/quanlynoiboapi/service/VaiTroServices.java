package com.example.quanlynoiboapi.service;

import com.example.quanlynoiboapi.reponsitory.VaiTroRepository;
import com.example.quanlynoiboapi.dto.VaiTroDTO;
import com.example.quanlynoiboapi.mapper.VaitroMapper;
import com.example.quanlynoiboapi.model.VaiTro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VaiTroServices {
    private final VaiTroRepository vaiTroRepository;
    private final VaitroMapper vaitroMapper;

    public List<VaiTroDTO> getVaiTros() {
        return vaitroMapper.toVaiTroDTOs(vaiTroRepository.findAll());
    }

    public void addVaiTro(VaiTroDTO vaiTroDTO) {
        vaiTroRepository.save(vaitroMapper.toVaiTro(vaiTroDTO));
    }

    public void delete(Integer id) {
        vaiTroRepository.deleteById(id);
    }

    public VaiTroDTO getVaiTroId(Integer id) {
        Optional<VaiTro> vaiTro = vaiTroRepository.findById(id);
        if (vaiTro.isPresent()) {
            return vaitroMapper.toVaiTroDTO(vaiTro.get());
        }

        return null;
    }

}
