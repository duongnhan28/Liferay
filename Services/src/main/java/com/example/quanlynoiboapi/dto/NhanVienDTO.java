package com.example.quanlynoiboapi.dto;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;


@Data
public class NhanVienDTO implements Serializable {

    private Integer id;
    private long userId;
    private String tenNhanVien;
    private Date ngaySinh;
    private boolean gioiTinh;
    private double luongChinhThuc;
    private String sdt;
    private double thueTNCN;
}
