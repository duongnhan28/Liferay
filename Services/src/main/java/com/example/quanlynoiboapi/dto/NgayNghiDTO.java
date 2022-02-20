package com.example.quanlynoiboapi.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class NgayNghiDTO implements Serializable {

    private int id;
    private float soNgayNghi;
    private float soNgayNghiPhep;
    private float soNgayChuaNghi;

}
