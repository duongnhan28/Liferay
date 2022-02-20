package com.example.quanlynoiboapi.reponsitory;

import com.example.quanlynoiboapi.model.NhanVienDuAn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVienDuAnReponsitory extends JpaRepository<NhanVienDuAn, Integer> {
    List<NhanVienDuAn> findNhanVienDuAnByNhanVien_Id(Integer id);

    List<NhanVienDuAn> findNhanVienDuAnByDuAn_Id(Integer id);
}
