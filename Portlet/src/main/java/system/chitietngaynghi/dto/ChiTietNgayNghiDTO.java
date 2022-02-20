package system.chitietngaynghi.dto;

import lombok.Data;
import system.ngaynghi.dto.NgayNghiDTO;
import system.nhanvien.dto.NhanVienDTO;

@Data
public class ChiTietNgayNghiDTO {

    private Integer id;
    private float soNgayNghi;

    private NhanVienDTO nhanVien;
    private NgayNghiDTO ngayNghi;
}
