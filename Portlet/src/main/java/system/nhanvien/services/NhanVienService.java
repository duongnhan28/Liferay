package system.nhanvien.services;


import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import system.nhanvien.clients.NhanVienClient;
import system.nhanvien.dto.NhanVienDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NhanVienService {
    private final NhanVienClient nhanVienClient;

    public List<NhanVienDTO> getNhanViens() throws Exception {
        UserLocalServiceUtil.getUsers(-1,-1);
        return nhanVienClient.getNhanViens();
    }

    public NhanVienDTO findByNhanVienId(Integer id){

        return nhanVienClient.getNhanVien(id);
    }

    public NhanVienDTO findByNhanVienUserId(long id){

            return nhanVienClient.getNhanVienByUserId(id);
    }


    public NhanVienDTO getNhanVien (Integer id) {

        if (id > 0) {
            return findByNhanVienId(id);
        }
        return new NhanVienDTO();
    }
    public NhanVienDTO getNhanVienByUserId (long id) {

        if (id > 0) {
            return findByNhanVienUserId(id);
        }
        return new NhanVienDTO();
    }

    public boolean saveNhanVien (NhanVienDTO nhanVienDTO) {

        return nhanVienClient.addNhanVien(nhanVienDTO);
    }

    public boolean deleteNhanVien (Integer id) {
        return nhanVienClient.deleteNhanVien(id);
    }

    public boolean deleteNhanVienByUserId (long userId) throws Exception {
        UserLocalServiceUtil.deleteUser(userId);
        return nhanVienClient.deleteNhanVienByUserId(userId);
    }

}
