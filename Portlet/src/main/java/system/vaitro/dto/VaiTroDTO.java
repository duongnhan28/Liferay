package system.vaitro.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class VaiTroDTO {

    private Integer id;
    @NotNull(message = "Mời bạn nhập tên vai trò")
    private String tenVaiTro;
}
