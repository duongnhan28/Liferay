package system.nhanvienduan.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import system.nhanvienduan.clients.NhanVienDuAnClient;
import system.nhanvienduan.dto.NhanVienDuAnDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NhanVienDuAnService {

    private final NhanVienDuAnClient nhanVienDuAnClient;

    public List<NhanVienDuAnDTO> getNhanVienDuAns() throws Exception {

        return nhanVienDuAnClient.getNhanVienDuAns();
    }

    public NhanVienDuAnDTO findByNhanVienDuAnId(Integer id) {

        return nhanVienDuAnClient.getNhanVienDuAn(id);
    }

    public NhanVienDuAnDTO getNhanVienDuAn(Integer id) {

        if (id > 0) {

            return findByNhanVienDuAnId(id);
        }

        return new NhanVienDuAnDTO();
    }

    public List<NhanVienDuAnDTO> findNhanVienByDuAn(Integer id) throws Exception {

        return nhanVienDuAnClient.getNhanVienByDuAn(id);
    }

    public List<NhanVienDuAnDTO> findDuAnByNhanVien(Integer id) throws Exception {

        return nhanVienDuAnClient.getDuAnByNhanVien(id);
    }

    public boolean saveNhanVienDuAn(NhanVienDuAnDTO nhanVienDuAnDTO) {

        return nhanVienDuAnClient.addNhanVienDuAn(nhanVienDuAnDTO);
    }
    public List<NhanVienDuAnDTO> findNhanVienDuAnByNhanVien(Integer id) throws Exception {
        return nhanVienDuAnClient.getNhanVienDuAnByNhanVien(id);
    }

    public boolean deleteNhanVienDuAn(Integer id) {

        return nhanVienDuAnClient.deleteNhanVienDuAn(id);
    }
}
