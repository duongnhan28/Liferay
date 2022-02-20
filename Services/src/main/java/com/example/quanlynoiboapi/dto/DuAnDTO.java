package com.example.quanlynoiboapi.dto;

import com.example.quanlynoiboapi.model.TrangThaiDuAn;
import lombok.Data;
import java.io.Serializable;
import java.sql.Date;

@Data
public class DuAnDTO implements Serializable {

    private Integer id;
    private String tenDuAn;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private TrangThaiDuAn trangThai;
}
