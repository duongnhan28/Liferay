package com.example.quanlynoiboapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class NgayNghi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float soNgayNghi;
    private float soNgayNghiPhep;
    private float soNgayChuaNghi;

    @OneToMany(mappedBy = "ngayNghi",fetch = FetchType.LAZY)
    @JsonBackReference
    List<ChiTietNgayNghi> chiTietNgayNghis;
}
