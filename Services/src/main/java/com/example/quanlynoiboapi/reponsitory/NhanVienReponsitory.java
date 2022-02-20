package com.example.quanlynoiboapi.reponsitory;

import com.example.quanlynoiboapi.model.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NhanVienReponsitory extends JpaRepository<NhanVien,Integer> {
    NhanVien findByUserId(Long id);

}
