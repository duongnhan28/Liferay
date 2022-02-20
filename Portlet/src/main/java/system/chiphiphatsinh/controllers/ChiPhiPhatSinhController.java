package system.chiphiphatsinh.controllers;

import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import system.chiphiphatsinh.dto.ChiPhiPhatSinhDTO;
import system.chiphiphatsinh.services.ChiPhiPhatSinhService;
import system.duan.dto.DuAnDTO;
import system.nhanvien.dto.NhanVienDTO;
import system.nhanvien.services.NhanVienService;
import system.ot.dto.OTDTO;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("VIEW")
@RequiredArgsConstructor
public class ChiPhiPhatSinhController {
    private final ChiPhiPhatSinhService chiPhiPhatSinhService;
    private final NhanVienService nhanVienService;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @RenderMapping
    public String view(Model model, RenderRequest request, RenderResponse response) throws Exception {
        User user = PortalUtil.getUser(request);
        long userId= PortalUtil.getUserId(request);

        ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
        List<Role> userRoles= RoleLocalServiceUtil.getUserRoles(themeDisplay.getUserId());
        model.addAttribute("userRoles",userRoles);

        for (Role role : userRoles) {
            if (role.getName().equals("Administrator")) {

                List<ChiPhiPhatSinhDTO> listView =  chiPhiPhatSinhService.getCPPSs();
                model.addAttribute("listView", listView);

                return "list";
            }
        }
        List<ChiPhiPhatSinhDTO> chiPhiPhatSinhDTOS = chiPhiPhatSinhService.getCPPSByUserId(userId);
        model.addAttribute("CPPSByUser", chiPhiPhatSinhDTOS);

        return "list";
    }

    @RenderMapping(params = "action=addNew")
    public String addNew(RenderRequest request, RenderResponse response, Model model,
                         @RequestParam(value = "id", defaultValue = "0") Integer id,
                         @RequestParam(required = false, defaultValue = "true") String validation) throws Exception {

        ChiPhiPhatSinhDTO item = new ChiPhiPhatSinhDTO();
        User user = PortalUtil.getUser(request);
        long userId= PortalUtil.getUserId(request);

        ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
        List<Role> userRoles= RoleLocalServiceUtil.getUserRoles(themeDisplay.getUserId());
        model.addAttribute("userRoles",userRoles);
        for (Role role : userRoles) {
            if (role.getName().equals("Administrator")) {

                if (validation.equals("false")) {
                    SessionErrors.add(request, "alert-error");
                    return "/form";
                }
                if (id > 0) {
                    item = chiPhiPhatSinhService.getChiPhiPhatSinh(id);
                }

                model.addAttribute("item", item);

                List<NhanVienDTO> list=nhanVienService.getNhanViens();
                model.addAttribute("list",list);

                return "form";
            }
        }
        ChiPhiPhatSinhDTO chiPhiPhatSinhDTO = chiPhiPhatSinhService.findByCPPSId(id);

        if (chiPhiPhatSinhDTO != null) {
            item = chiPhiPhatSinhDTO;
        }

        item.setId(id);
        model.addAttribute("item", item);

        NhanVienDTO nhanVien = nhanVienService.findByNhanVienUserId(userId);
        model.addAttribute("nhanVien", nhanVien);

        return "form";
    }

    @ActionMapping(params = "action=addNew")
    public void addNew(ActionRequest actionRequest, ActionResponse actionResponse, SessionStatus sessionStatus,
                       @ModelAttribute("item") ChiPhiPhatSinhDTO chiPhiPhatSinhDTO, BindingResult result, Model model) throws Exception {

        chiPhiPhatSinhService.saveChiPhiPhatSinh(chiPhiPhatSinhDTO);
        sessionStatus.setComplete();
        SessionMessages.add(actionRequest, "form-success");
        actionResponse.setRenderParameter("action", "");
    }

    @ActionMapping(params = "action=delete")
    public void delete(ActionRequest request, ActionResponse response, SessionStatus sessionStatus,
                       @RequestParam(value = "id", defaultValue = "0") Integer id) {
        if (id > 0) {
            chiPhiPhatSinhService.deleteChiPhiPhatSinh(id);
        }

        sessionStatus.setComplete();
        SessionMessages.add(request, "form-success");
        response.setRenderParameter("action","");
    }
}