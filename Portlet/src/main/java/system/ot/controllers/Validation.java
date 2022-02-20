package system.ot.controllers;

import system.ot.dto.OTDTO;

public class Validation {
        public static Boolean validate(OTDTO otdto){
            if (otdto.getNgayBatDauOT().equals(otdto.getNgayKetThucOT())
                    || otdto.getNgayBatDauOT().after(otdto.getNgayKetThucOT())){
                return false;
            } else {
                return true;
            }
    }

}
