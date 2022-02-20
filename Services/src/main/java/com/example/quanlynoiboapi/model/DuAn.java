package com.example.quanlynoiboapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Data
@Entity
    public class DuAn implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tenDuAn;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private TrangThaiDuAn trangThai;

    @OneToMany(mappedBy = "duAn",fetch = FetchType.LAZY)
    @JsonBackReference
    List<NhanVienDuAn> nhanVienDuAns;

    @JsonIgnore
    @OneToMany(mappedBy = "duAn",fetch = FetchType.LAZY)
    private List<OT> ots;
}
