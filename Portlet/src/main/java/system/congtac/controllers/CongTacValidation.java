package system.congtac.controllers;

import system.congtac.dto.CongTacDTO;

public class CongTacValidation {
    public static Boolean validate(CongTacDTO congTacDTO){
        if (congTacDTO.getNgayBatDau().equals(congTacDTO.getNgayKetThuc())
                || congTacDTO.getNgayBatDau().after(congTacDTO.getNgayKetThuc())){
            return false;
        } else {
            return true;
        }
    }
}
