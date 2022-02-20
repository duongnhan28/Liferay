package system.ngaynghinhanvien.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import system.chiphiphatsinh.dto.ChiPhiPhatSinhDTO;
import system.ngaynghinhanvien.clients.NgayNghiNhanVienClient;
import system.ngaynghinhanvien.dto.NgayNghiNhanVienDTO;

import java.util.List;


@Service
@Component
@RequiredArgsConstructor
public class NgayNghiNhanVienService {
    private final NgayNghiNhanVienClient ngayNghiNhanVienClient;

    public List<NgayNghiNhanVienDTO> gets() throws Exception {
        return ngayNghiNhanVienClient.gets();
    }

    public NgayNghiNhanVienDTO findById(Integer id) {
        return ngayNghiNhanVienClient.getById(id);
    }

    public List<NgayNghiNhanVienDTO> getNNNVByUserId(long userId) {

        return ngayNghiNhanVienClient.getNNNVByUserId(userId);
    }

    public NgayNghiNhanVienDTO getById(Integer id) {
        if (id > 0) {
            return findById(id);
        }
        return new NgayNghiNhanVienDTO();
    }

    public boolean save(NgayNghiNhanVienDTO ngayNghiNhanVienDTO) {
        return ngayNghiNhanVienClient.add(ngayNghiNhanVienDTO);
    }

    public boolean delete(Integer id) {
        return ngayNghiNhanVienClient.delete(id);


    }
}
