package system.duan.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import system.duan.clients.DuAnClient;
import system.duan.dto.DuAnDTO;
import system.ot.dto.OTDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DuAnService {

    private final DuAnClient duAnClient;

    public List<DuAnDTO> getDuAns() throws Exception {

        return duAnClient.getDuANs();
    }

    public List<DuAnDTO> getByNhanVien(Integer id) throws Exception {

        return duAnClient.getByNhanVien(id);
    }

    public DuAnDTO findDuAnById(Integer id) {

        return duAnClient.getDuAn(id);
    }

    public DuAnDTO getDuAn(Integer id) {

        if (id > 0) {

            return findDuAnById(id);
        }

        return new DuAnDTO();
    }

    public boolean saveDuAn(DuAnDTO duAnDTO) {

        return duAnClient.addDuAn(duAnDTO);
    }

    public boolean deleteDuAn(Integer id) {

        return duAnClient.delete(id);
    }
    public List<DuAnDTO> getDuAnByUserId(long userId) {

        return duAnClient.getDuAnByUserId(userId);
    }

}
