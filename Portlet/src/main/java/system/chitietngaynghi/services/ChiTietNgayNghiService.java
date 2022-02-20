package system.chitietngaynghi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import system.chitietngaynghi.clients.ChiTietNgayNghiClient;
import system.chitietngaynghi.dto.ChiTietNgayNghiDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChiTietNgayNghiService {

    private final ChiTietNgayNghiClient chiTietNgayNghiClient;

    public List<ChiTietNgayNghiDTO> getCTNgayNghis() throws Exception {
        return chiTietNgayNghiClient.getCTNgayNghis();
    }

    public ChiTietNgayNghiDTO findByCTNgayNghiId(Integer id){

        return chiTietNgayNghiClient.getCTNgayNghi(id);
    }

    public ChiTietNgayNghiDTO getCTNgayNghi (Integer id) {

        if (id > 0) {
            return findByCTNgayNghiId(id);
        }
        return new ChiTietNgayNghiDTO();
    }

    public boolean save(ChiTietNgayNghiDTO chiTietNgayNghiDTO) {

        return chiTietNgayNghiClient.addCTNgayNghi(chiTietNgayNghiDTO);
    }

    public boolean delete(Integer id) {

        return chiTietNgayNghiClient.delete(id);
    }
}