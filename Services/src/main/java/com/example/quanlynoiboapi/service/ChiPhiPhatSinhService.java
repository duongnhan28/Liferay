package com.example.quanlynoiboapi.service;

import com.example.quanlynoiboapi.dto.ChiPhiPhatSinhDTO;
import com.example.quanlynoiboapi.mapper.ChiPhiPhatSinhMapper;
import com.example.quanlynoiboapi.model.ChiPhiPhatSinh;
import com.example.quanlynoiboapi.reponsitory.ChiPhiPhatSinhReponsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ChiPhiPhatSinhService {

    private final ChiPhiPhatSinhReponsitory chiPhiPhatSinhReponsitory;
    private final ChiPhiPhatSinhMapper chiPhiPhatSinhMapper;

    public List<ChiPhiPhatSinhDTO> getAllCPPS() {
        return chiPhiPhatSinhMapper.toCPPSDTOs(chiPhiPhatSinhReponsitory.findAll());
    }

    public ChiPhiPhatSinhDTO getCPPSId(Integer id) {
        Optional<ChiPhiPhatSinh> chiPhiPhatSinh = chiPhiPhatSinhReponsitory.findById(id);

        if (chiPhiPhatSinh.isPresent()) {
            return chiPhiPhatSinhMapper.toCPPSDTO(chiPhiPhatSinh.get());
        }
        return null;
    }

    public void addCPPS(ChiPhiPhatSinhDTO chiPhiPhatSinhDTO) {
        chiPhiPhatSinhReponsitory.save(chiPhiPhatSinhMapper.toCPPS(chiPhiPhatSinhDTO));
    }

    public void updateCPPS(ChiPhiPhatSinhDTO chiPhiPhatSinhDTO) {
        chiPhiPhatSinhReponsitory.save(chiPhiPhatSinhMapper.toCPPS(chiPhiPhatSinhDTO));
    }

    public void deleteCPPS(Integer id) {
        chiPhiPhatSinhReponsitory.deleteById(id)
        ;
    }
}