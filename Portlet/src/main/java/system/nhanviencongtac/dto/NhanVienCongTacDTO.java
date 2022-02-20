package system.nhanviencongtac.dto;

import lombok.Data;
import system.congtac.dto.CongTacDTO;
import system.nhanvien.dto.NhanVienDTO;

@Data
public class NhanVienCongTacDTO {

    private Integer id;
    private NhanVienDTO nhanVien;
    private CongTacDTO congTac;
}
