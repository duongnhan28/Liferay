package system.nhanvienduan.dto;

import lombok.Data;;
import system.duan.dto.DuAnDTO;
import system.nhanvien.dto.NhanVienDTO;
import system.vaitro.dto.VaiTroDTO;

@Data
public class NhanVienDuAnDTO {

    private Integer id;
    private DuAnDTO duAn;
    private NhanVienDTO nhanVien;
    private VaiTroDTO vaiTro;
}
