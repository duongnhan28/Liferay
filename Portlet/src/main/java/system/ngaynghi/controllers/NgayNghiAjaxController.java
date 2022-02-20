package system.ngaynghi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import system.ngaynghi.dto.NgayNghiDTO;
import system.ngaynghi.services.NgayNghiService;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller(value = "AjaxController")
@RequestMapping("VIEW")
public class NgayNghiAjaxController {

    @Autowired
    private NgayNghiService ngayNghiService;

    @ResourceMapping(value = "findState")
    public void findState(ResourceRequest request, ResourceResponse response) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<NgayNghiDTO> list =ngayNghiService.getNgayNghis();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        JSONArray array =new JSONArray();

        for(NgayNghiDTO ngayNghiDTO:list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("title", ngayNghiDTO.getTitle());
            jsonObject.put("start", df.format(ngayNghiDTO.getStart()));
            jsonObject.put("end", df.format(ngayNghiDTO.getEnd()));
            jsonObject.put("description", ngayNghiDTO.getDescription());
            array.put(jsonObject);
        }
        response.getWriter().println(array.toString());
    }
}