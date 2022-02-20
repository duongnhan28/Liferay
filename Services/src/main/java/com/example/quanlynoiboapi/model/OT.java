package com.example.quanlynoiboapi.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class OT implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date ngayBatDauOT;
    private Date ngayKetThucOT;
    @Min(value = 1, message = "Thời gian OT phải lớn hơn 1")
    @Max(value = 8, message = "Thời gian OT phải nhỏ hơn 8")
    private Integer timeOT;
    @NotBlank(message = "Lý Do không được trống")
    private String lyDo;
    private TinhTrangDuyet tinhTrangDuyet;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "nhanVienId")
    @ToString.Exclude
    private NhanVien nhanVien;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "duAnId")
    @ToString.Exclude
    private DuAn duAn;
}
