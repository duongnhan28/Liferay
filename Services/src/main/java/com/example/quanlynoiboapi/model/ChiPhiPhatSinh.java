package com.example.quanlynoiboapi.model;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class ChiPhiPhatSinh implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tenChiPhi;
    private Date thang;
    private String noiDung;
    private double soTien;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "nhanVienId")
    private NhanVien nhanVien;
}