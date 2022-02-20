package system.chitietcongtac.dto;

import lombok.Data;
import system.congtac.dto.CongTacDTO;
import system.nhanvienduan.dto.NhanVienDuAnDTO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ChiTietCongTacDTO {

    private int id;
    private NhanVienDuAnDTO nhanVienDuAn;
    @NotNull(message = "Mời bạn nhập tên chi phí")
    private String tenChiPhi;
    @NotNull(message = "Mời bạn nhập đơn vị tính")
    private String donViTinh;
    @NotNull(message = "Mời bạn nhập số lượng")
    @Min(value = 1, message = "Số lượng phải lớn hơn 0")
    @Max(value = 100, message = "Số lượng phải nhỏ hơn 100")
    private Integer soLuong;
    @NotNull(message = "Mời bạn đơn giá")
    @Min(value = 1000, message = "Đơn giá phải lớn hơn hoặc bằng 1.000")
    @Max(value = 10000000, message = "Đơn giá phải nhỏ hơn 10.000.000")
    private double donGia;
    private double thanhTien;
    private CongTacDTO congTac;
    private boolean trangThai;
}
