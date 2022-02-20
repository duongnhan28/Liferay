package system.duan.controllers;

import system.duan.dto.DuAnDTO;

public class DuAnValidation {
    public static Boolean validate(DuAnDTO duAnDTO){
        if (duAnDTO.getNgayBatDau().equals(duAnDTO.getNgayKetThuc())
                || duAnDTO.getNgayBatDau().after(duAnDTO.getNgayKetThuc())){
            return false;
        } else {
            return true;
        }
    }
}
