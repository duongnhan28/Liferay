package system.congtac.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import system.congtac.clients.CongTacClient;
import system.congtac.dto.CongTacDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CongTacService {

    private final CongTacClient congTacClient;

    public List<CongTacDTO> getCongTacs() throws Exception {

        return congTacClient.getCongTacs();
    }

    public List<CongTacDTO> findCongTacByDuAn(Integer id) throws Exception {

        return congTacClient.getCongTacByDuAn(id);
    }

    public CongTacDTO findCongTacById(Integer id) {

        return congTacClient.getCongTac(id);
    }

    public CongTacDTO getCongTac(Integer id) {

        if (id > 0) {

            return findCongTacById(id);
        }

        return new CongTacDTO();
    }

    public boolean saveCongTac(CongTacDTO congTacDTO) {

        return congTacClient.addCongTac(congTacDTO);
    }

    public boolean deleteCongTac(Integer id) {

        return congTacClient.delete(id);
    }
}
