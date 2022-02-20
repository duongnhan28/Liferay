package system.ngaynghinhanvien.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import system.duan.dto.DuAnDTO;
import system.nhanvien.dto.NhanVienDTO;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class NgayNghiNhanVienDTO {

    private Integer id;
    private Double soNgayDaNghi;
    private Double soNgayNghiPhep;
    private Double soNgayNghiTon;

    private NhanVienDTO nhanVien;
}