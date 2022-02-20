package system.congtac.dto;

import lombok.Data;
import system.duan.dto.DuAnDTO;
import system.nhanvien.dto.NhanVienDTO;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CongTacDTO {

    private Integer id;

    private DuAnDTO duAn;
    private NhanVienDTO nhanVien;
    @NotNull(message = "Ngày bắt đầu phải nhỏ hơn ngày kết thúc")
    private Date ngayBatDau;
    @NotNull(message = "Ngày kết thúc phải lón hơn ngày bắt đầu")
    private Date ngayKetThuc;
    @NotNull(message = "Mời bạn nhập nhiệm vụ")
    private String nhiemVu;
    @NotNull(message = "Mời bạn nhập địa điểm")
    private String diaDiem;
    @NotNull(message = "Mời bạn nhập ghi chú")
    private String ghiChu;
    private TrangThaiCongTac trangThai;
    private double tienUng;
}
