package system.ot.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import system.ot.clients.OTClient;
import system.ot.dto.OTDTO;

import java.util.List;


@Service
@Component
@RequiredArgsConstructor
public class OTServices {
    private final OTClient otClient;

    public List<OTDTO> getOTs(){
        return otClient.getOTs();
    }

    public OTDTO findByOTId(Integer id) {
        return otClient.getOT(id);
    }

    public OTDTO getOT(Integer id) {
        if (id > 0) {
            return findByOTId(id);
        }
        return new OTDTO();
    }

    public boolean saveOT(OTDTO otdto) {
        return otClient.addOT(otdto);
    }

    public boolean deleteOT(Integer id) {
        return otClient.deleteOT(id);
    }

    public List<OTDTO> getOTByUserId(long userId) {

        return otClient.getOTByUserId(userId);
    }

    public boolean acpectOT(Integer id) {
        return otClient.acceptOT(id);
    }

    public boolean denyOT(Integer id) {
        return otClient.denyOT(id);
    }

    public List<OTDTO> getByDuAnId(Integer id) {

        return otClient.getByDuAnId(id);
    }

}
