package com.example.quanlynoiboapi.model;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class NhanVienDuAn implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "duAnId")
    private DuAn duAn;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "nhanVienId")
    private NhanVien nhanVien;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "vaiTroId")
    private VaiTro vaiTro;


}