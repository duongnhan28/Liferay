package com.example.quanlynoiboapi.dto;

import com.example.quanlynoiboapi.model.TinhTrangDuyet;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OTDTO implements Serializable {
    private Integer id;
    private Date ngayBatDauOT;
    private Date ngayKetThucOT;
    private Integer timeOT;
    private String lyDo;
    private NhanVienDTO nhanVien;
    private DuAnDTO duAn;
    private TinhTrangDuyet tinhTrangDuyet;

}
