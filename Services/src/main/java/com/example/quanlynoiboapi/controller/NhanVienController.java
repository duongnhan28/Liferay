package com.example.quanlynoiboapi.controller;


import com.example.quanlynoiboapi.dto.NhanVienDTO;
import com.example.quanlynoiboapi.service.NhanVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/nhanVien")
public class NhanVienController {

    private final NhanVienService nhanVienService;

    @GetMapping
    public List<NhanVienDTO> getAllNhanVien(){
        return nhanVienService.getAllNhanVien();
    }

    @PostMapping("/add")
    public void createNhanVien(@RequestBody NhanVienDTO nhanVienDTO) {
        nhanVienService.addNhanVien(nhanVienDTO);
    }

    @GetMapping("/{id}")
    public NhanVienDTO getNhanVienById(@PathVariable  Integer id){
        return nhanVienService.getNhanVienId(id);
    }

    @PutMapping("/update/{id}")
    public void updateNhanVien(@RequestBody NhanVienDTO nhanVienDTO) {
        nhanVienService.updateNhanVien(nhanVienDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteNhanVien(@PathVariable Integer id){
        nhanVienService.deleteNhanVien(id);
    }

    @GetMapping("/user/{id}")
    public NhanVienDTO getNhanVienByUserId(@PathVariable  Long id){
        return nhanVienService.getNhanVienByUserId(id);
    }

}