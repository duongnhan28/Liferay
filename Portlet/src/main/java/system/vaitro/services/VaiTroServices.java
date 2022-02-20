package system.vaitro.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import system.vaitro.clients.VaiTroClient;
import system.vaitro.dto.VaiTroDTO;

import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class VaiTroServices {
    private final VaiTroClient vaiTroClient;

    public List<VaiTroDTO> getVaiTros() throws Exception {
        return vaiTroClient.getVaiTros();
    }

    public VaiTroDTO findVaiTroById(Integer id) {
        return vaiTroClient.getVaiTro(id);
    }

    public VaiTroDTO getVaiTro(Integer id) {
        if (id > 0) {
            return findVaiTroById(id);
        }
        return new VaiTroDTO();
    }

    public boolean saveVaiTro(VaiTroDTO vaiTroDTO) {
        return vaiTroClient.addVaiTro(vaiTroDTO);
    }

    public boolean deleteVaiTro(Integer id) {
        return vaiTroClient.deleteVaiTro(id);


    }
}
