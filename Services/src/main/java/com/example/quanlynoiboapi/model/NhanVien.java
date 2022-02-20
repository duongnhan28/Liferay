package com.example.quanlynoiboapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class NhanVien implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private long userId;
    private String tenNhanVien;
    private Date ngaySinh;
    private boolean gioiTinh;
    private double luongChinhThuc;
    private String sdt;
    private double thueTNCN;

    @JsonIgnore
    @OneToMany(mappedBy = "nhanVien",fetch = FetchType.LAZY)
    private List<OT> ots;

    @OneToMany(mappedBy = "nhanVien",fetch = FetchType.LAZY)
    @JsonBackReference
    List<ChiTietNgayNghi> chiTietNgayNghis;

    @OneToMany(mappedBy = "nhanVien",fetch = FetchType.LAZY)
    @JsonBackReference
    List<NhanVienDuAn> nhanVienDuAns;

    @OneToMany(mappedBy = "nhanVien",fetch = FetchType.LAZY)
    @JsonBackReference
    List<ChiPhiPhatSinh> chiPhiPhatSinhs;

}
