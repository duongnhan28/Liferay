package system.ngaynghi.controllers;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.servlet.ModelAndView;
import system.chiphiphatsinh.dto.ChiPhiPhatSinhDTO;
import system.duan.dto.DuAnDTO;
import system.ngaynghi.dto.NgayNghiDTO;
import system.ngaynghi.services.NgayNghiService;
import system.nhanvien.dto.NhanVienDTO;
import system.ot.dto.OTDTO;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("VIEW")
@RequiredArgsConstructor
public class NgayNghiController {

    private final NgayNghiService ngayNghiService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @RenderMapping
    public String view(Model model) throws Exception {
        List<NgayNghiDTO> events = ngayNghiService.getNgayNghis();
        model.addAttribute("events", events);
        return "list";
    }

    @RenderMapping(params = "action=addNew")
    public String addNew(RenderRequest request, RenderResponse response, Model model,
                         @RequestParam(value = "id", defaultValue = "0") Integer id,
                         @RequestParam(required = false, defaultValue = "true") String validation) throws Exception {

        if (validation.equals("false")) {
            SessionErrors.add(request, "alert-error");
            return "/form";
        }

        NgayNghiDTO item = new NgayNghiDTO();

        if (id > 0) {
            item = ngayNghiService.get(id);
        }
        model.addAttribute("item", item);

        return "form";
    }

    @ActionMapping(params = "action=addNew")
    public void addNew(ActionRequest actionRequest, ActionResponse actionResponse, SessionStatus sessionStatus,
                       @ModelAttribute("item") NgayNghiDTO ngayNghiDTO, BindingResult result, Model model) {

        ngayNghiService.save(ngayNghiDTO);
        sessionStatus.setComplete();
        SessionMessages.add(actionRequest, "form-success");
        actionResponse.setRenderParameter("action","");
    }

    @ActionMapping(params = "action=delete")
    public void delete(ActionRequest request, ActionResponse response, SessionStatus sessionStatus,
                       @RequestParam(value = "id", defaultValue = "0") Integer id) {
        if (id > 0) {
            ngayNghiService.delete(id);
        }

        sessionStatus.setComplete();
        SessionMessages.add(request, "form-success");
        response.setRenderParameter("action","");
    }
}