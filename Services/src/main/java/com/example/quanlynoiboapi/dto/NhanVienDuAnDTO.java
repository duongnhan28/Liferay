package com.example.quanlynoiboapi.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class NhanVienDuAnDTO implements Serializable {

    private Integer id;
    private DuAnDTO duAn;
    private NhanVienDTO nhanVien;
    private VaiTroDTO vaiTro;
}
