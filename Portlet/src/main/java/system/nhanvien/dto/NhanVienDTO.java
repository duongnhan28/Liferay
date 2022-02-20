package system.nhanvien.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;


@Data
public class NhanVienDTO {
    private Integer id;
    private String tenNhanVien;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date ngaySinh;
    private boolean gioiTinh;
    private double luongChinhThuc;
    private String sdt;
    private double thueTNCN;
    private long userId;


}
