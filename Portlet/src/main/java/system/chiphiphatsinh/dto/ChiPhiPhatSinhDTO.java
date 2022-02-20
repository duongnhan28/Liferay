package system.chiphiphatsinh.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import system.nhanvien.dto.NhanVienDTO;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class ChiPhiPhatSinhDTO {

    private Integer id;
    private String tenChiPhi;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "MM-yyyy")
    private Date thang;

    private String noiDung;
    private double soTien;
    private NhanVienDTO nhanVien;
}
