package system.nhanviencongtac.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import system.nhanviencongtac.clients.NhanVienCongTacClient;
import system.nhanviencongtac.dto.NhanVienCongTacDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NhanVienCongTacService {
    private final NhanVienCongTacClient nhanVienCongTacClient;

    public List<NhanVienCongTacDTO> getAll() throws Exception {

        return nhanVienCongTacClient.getAll();
    }

    public List<NhanVienCongTacDTO> getbByCongTac(Integer id) throws Exception {

        return nhanVienCongTacClient.getByCongTac(id);
    }

    public NhanVienCongTacDTO findById(Integer id) {

        return nhanVienCongTacClient.getById(id);
    }

    public NhanVienCongTacDTO getById(Integer id) {

        if (id > 0) {

            return findById(id);
        }

        return new NhanVienCongTacDTO();
    }

    public boolean save(NhanVienCongTacDTO nhanVienCongTacDTO) {

        return nhanVienCongTacClient.add(nhanVienCongTacDTO);
    }

    public boolean delete(Integer id) {

        return nhanVienCongTacClient.delete(id);
    }
}
