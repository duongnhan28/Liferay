package com.example.quanlynoiboapi.controller;

import com.example.quanlynoiboapi.dto.NhanVienDTO;
import com.example.quanlynoiboapi.dto.NhanVienDuAnDTO;
import com.example.quanlynoiboapi.service.NhanVienDuAnService;
import com.example.quanlynoiboapi.service.NhanVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/nhanvienduan")
public class NhanVienDuAnController {

    private final NhanVienDuAnService nhanVienDuAnService;
    private final NhanVienService nhanVienService;

    @GetMapping
    public List<NhanVienDuAnDTO> getAllNhanVienDuAns() {
        return nhanVienDuAnService.getNhanVienDuAns();
    }

    @GetMapping("/{id}")
    public NhanVienDuAnDTO getById(@PathVariable Integer id) {
        return nhanVienDuAnService.getNhanVienDuAnById(id);
    }

    @GetMapping("/nhanvien/{id}")
    public List<NhanVienDuAnDTO> getByNhanVien(@PathVariable Integer id) {
        return nhanVienDuAnService.getByNhanVien(id);
    }

    @GetMapping("/nhanvienduan/{id}")
    public List<NhanVienDuAnDTO> getDuAnByNhanVien(@PathVariable Integer id) {
        return nhanVienDuAnService.getDuAnByNhanVien(id);
    }

    @GetMapping("/duan/{id}")
    public List<NhanVienDuAnDTO> getByDuAn(@PathVariable Integer id) {
        return nhanVienDuAnService.getByDuAn(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody @Valid NhanVienDuAnDTO nhanVienDuAnDTO) {
        nhanVienDuAnService.addNhanVienDuAn(nhanVienDuAnDTO);
    }

    @PutMapping("update/{id}")
    public void edit(@RequestBody @Valid NhanVienDuAnDTO nhanVienDuAnDTO) {
        nhanVienDuAnService.addNhanVienDuAn(nhanVienDuAnDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        nhanVienDuAnService.delete(id);
    }

    @GetMapping("/user/{id}")
    public NhanVienDTO getNhanVienByUserId(@PathVariable  Integer id){
        return nhanVienService.getNhanVienByUserId(id);
    }
}
