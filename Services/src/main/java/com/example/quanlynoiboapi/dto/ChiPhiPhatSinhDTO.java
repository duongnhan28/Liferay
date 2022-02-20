package com.example.quanlynoiboapi.dto;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class ChiPhiPhatSinhDTO implements Serializable {

    private Integer id;
    private String tenChiPhi;
    private Date thang;
    private String noiDung;
    private double soTien;

    private NhanVienDTO nhanVien;

}