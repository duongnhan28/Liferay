package com.example.quanlynoiboapi.controller;

import com.example.quanlynoiboapi.dto.VaiTroDTO;
import com.example.quanlynoiboapi.service.VaiTroServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vaitro")
public class VaiTroController {

    private final VaiTroServices vaiTroServices;

    @GetMapping
    public List<VaiTroDTO> getVaitros() {
        return vaiTroServices.getVaiTros();
    }

    @GetMapping("/{id}")
    public VaiTroDTO getById(@PathVariable Integer id) {
        return vaiTroServices.getVaiTroId(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody VaiTroDTO vaiTroDTO) {
        vaiTroServices.addVaiTro(vaiTroDTO);
    }

    @PutMapping("update/{id}")
    public void edit(@RequestBody VaiTroDTO vaiTroDTO) {
        vaiTroServices.addVaiTro(vaiTroDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        vaiTroServices.delete(id);
    }
}
