package com.example.quanlynoiboapi.controller;

import com.example.quanlynoiboapi.dto.ChiPhiPhatSinhDTO;
import com.example.quanlynoiboapi.service.ChiPhiPhatSinhService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chiPhiPhatSinh")
public class ChiPhiPhatSinhController {
    private final ChiPhiPhatSinhService chiPhiPhatSinhService;

    @GetMapping
    public List<ChiPhiPhatSinhDTO> getAllCPPS() {
        return chiPhiPhatSinhService.getAllCPPS();
    }

    @PostMapping("/add")
    public void createCPPS(@RequestBody ChiPhiPhatSinhDTO chiPhiPhatSinhDTO) {
        chiPhiPhatSinhService.addCPPS(chiPhiPhatSinhDTO);
    }

    @GetMapping("/{id}")
    public ChiPhiPhatSinhDTO getCPPSById(@PathVariable Integer id) {
        return chiPhiPhatSinhService.getCPPSId(id);
    }

    @PutMapping("/update/{id}")
    public void updateCPPS(@RequestBody ChiPhiPhatSinhDTO chiPhiPhatSinhDTO) {
        chiPhiPhatSinhService.updateCPPS(chiPhiPhatSinhDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCPPS(@PathVariable Integer id) {
        chiPhiPhatSinhService.deleteCPPS(id);
    }
}