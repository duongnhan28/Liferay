package system.chitietngaynghi.controllers;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import system.chitietngaynghi.dto.ChiTietNgayNghiDTO;
import system.chitietngaynghi.services.ChiTietNgayNghiService;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;

@Controller
@RequestMapping("VIEW")
@RequiredArgsConstructor
public class ChiTietNgayNghiController {
    private final ChiTietNgayNghiService chiTietNgayNghiService;

    @RenderMapping
    public String view(Model model) throws Exception {

        List<ChiTietNgayNghiDTO> listView = chiTietNgayNghiService.getCTNgayNghis();
        model.addAttribute("listView", listView);

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

        ChiTietNgayNghiDTO item = new ChiTietNgayNghiDTO();

        if (id > 0) {
            item = chiTietNgayNghiService.getCTNgayNghi(id);
        }
        model.addAttribute("item", item);
        return "form";
    }

    @ActionMapping(params = "action=addNew")
    public void addNew(ActionRequest actionRequest, ActionResponse actionResponse, SessionStatus sessionStatus,
                       @ModelAttribute("item") ChiTietNgayNghiDTO chiTietNgayNghiDTO, BindingResult result, Model model) {

        chiTietNgayNghiService.save(chiTietNgayNghiDTO);
        sessionStatus.setComplete();
        SessionMessages.add(actionRequest, "form-success");
        actionResponse.setRenderParameter("action","");
    }

    @ActionMapping(params = "action=delete")
    public void delete(ActionRequest request, ActionResponse response, SessionStatus sessionStatus,
                       @RequestParam(value = "id", defaultValue = "0") Integer id) {
        if (id > 0) {
            chiTietNgayNghiService.delete(id);
        }
        sessionStatus.setComplete();
        SessionMessages.add(request, "form-success");
        response.setRenderParameter("action","");
    }
}