package system.chiphiphatsinh.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import system.chiphiphatsinh.clients.ChiPhiPhatSinhClient;
import system.chiphiphatsinh.dto.ChiPhiPhatSinhDTO;
import system.nhanvien.dto.NhanVienDTO;
import system.ot.dto.OTDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChiPhiPhatSinhService {

    private final ChiPhiPhatSinhClient chiPhiPhatSinhClient;

    public List<ChiPhiPhatSinhDTO> getCPPSs() throws Exception {
        return chiPhiPhatSinhClient.getChiPhiPhatSinhs();
    }

    public ChiPhiPhatSinhDTO findByCPPSId(Integer id){

        return chiPhiPhatSinhClient.getChiPhiPhatSinh(id);
    }
    public List<ChiPhiPhatSinhDTO> getCPPSByUserId(long userId) {

        return chiPhiPhatSinhClient.getCPPSByUserId(userId);
    }

    public ChiPhiPhatSinhDTO getChiPhiPhatSinh (Integer id) {

        if (id > 0) {
            return findByCPPSId(id);
        }
        return new ChiPhiPhatSinhDTO();
    }


    public boolean saveChiPhiPhatSinh (ChiPhiPhatSinhDTO chiPhiPhatSinhDTO) {

        return chiPhiPhatSinhClient.addChiPhiPhatSinh(chiPhiPhatSinhDTO);
    }

    public boolean deleteChiPhiPhatSinh(Integer id) {

        return chiPhiPhatSinhClient.deleteCPPS(id);
    }
}