package system.ngaynghi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import system.ngaynghi.clients.NgayNghiClient;
import system.ngaynghi.dto.NgayNghiDTO;

import java.util.*;

@Service
@RequiredArgsConstructor
public class NgayNghiService {

    private final NgayNghiClient ngayNghiClient;

    public List<NgayNghiDTO> getNgayNghis() throws Exception {
        return ngayNghiClient.getNgayNghis();
    }

    public NgayNghiDTO findByNNId(Integer id){

        return ngayNghiClient.get(id);
    }

    public NgayNghiDTO get (Integer id) {

        if (id > 0) {
            return findByNNId(id);
        }
        return new NgayNghiDTO();
    }

    public boolean save (NgayNghiDTO ngayNghiDTO) {

        return ngayNghiClient.add(ngayNghiDTO);
    }

    public boolean delete(Integer id) {

        return ngayNghiClient.delete(id);
    }
}
