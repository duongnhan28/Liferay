package com.example.quanlynoiboapi.reponsitory;

import com.example.quanlynoiboapi.model.VaiTro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaiTroRepository extends JpaRepository<VaiTro,Integer> {
}
