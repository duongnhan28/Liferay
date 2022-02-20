package system.nhanvienduan.controllers;

import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
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
import system.nhanvien.dto.NhanVienDTO;
import system.nhanvien.services.NhanVienService;
import system.nhanvienduan.dto.NhanVienDuAnDTO;
import system.nhanvienduan.services.NhanVienDuAnService;
import system.vaitro.dto.VaiTroDTO;
import system.vaitro.services.VaiTroService;

import javax.portlet.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("VIEW")
@RequiredArgsConstructor
public class NhanVienDuAnController {

    private final NhanVienDuAnService nhanVienDuAnService;
    private final NhanVienService nhanVienService;
    private final VaiTroService vaiTroService;


    @InitBinder
    public void initBinder(WebDataBinder binder) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @RenderMapping
    public String view(Model model, RenderRequest request, RenderResponse renderResponse, @RequestParam(value = "id") Integer id, @RequestParam(value = "name") String name) throws Exception, PortletException {

        ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
        List<Role> roles = themeDisplay.getUser().getRoles();
        List<Role> userRoles= RoleLocalServiceUtil.getUserRoles(themeDisplay.getUserId());

        model.addAttribute("userRoles", roles);
        model.addAttribute("duAnId", request.getParameter("id"));

        for (Role role : userRoles) {

            if (role.getName().equals("Administrator") || role.getName().equals("Kế toán")) {

                List<NhanVienDuAnDTO> listView = nhanVienDuAnService.findNhanVienByDuAn(id);
                model.addAttribute("listView", listView);
                model.addAttribute("tenDuAn", request.getParameter("name"));
                return "danhsach";
            }
        }

        List<NhanVienDuAnDTO> listView = nhanVienDuAnService.findNhanVienByDuAn(id);
        model.addAttribute("listView", listView);

        return "danhsach";
    }

    @RenderMapping(params = "action=add")
    public String themMoi(RenderRequest request, RenderResponse response, Model model,
                          @RequestParam(value = "id", defaultValue = "0") Integer id,
                          @RequestParam(value = "duAn.id") Integer duAnId,
                          @RequestParam(value = "name") String name,
                          @RequestParam(required = false, defaultValue = "true") String validation) throws Exception {


        if (validation.equals("false")) {

            SessionErrors.add(request, "alert-error");

            return "/themmoi";
        }

        NhanVienDuAnDTO item = new NhanVienDuAnDTO();

        if (id > 0) {

            item = nhanVienDuAnService.getNhanVienDuAn(id);
        }

        List<NhanVienDTO> listNhanVien = nhanVienService.getNhanViens();
        List<VaiTroDTO> listVaiTro = vaiTroService.getVaiTros();

        model.addAttribute("duAnId", request.getParameter("duAn.id"));
        model.addAttribute("tenDuAn", request.getParameter("name"));
        model.addAttribute("listNhanVien",listNhanVien);
        model.addAttribute("listVaiTro",listVaiTro);
        model.addAttribute("item", item);

        return "/themmoi";
    }

    @ActionMapping(params = "action=add")
    public void themMoi(ActionRequest actionRequest, ActionResponse actionResponse, SessionStatus sessionStatus,
                        @ModelAttribute("item") NhanVienDuAnDTO nhanVienDuAnDTO, BindingResult result, Model model) {

        nhanVienDuAnService.saveNhanVienDuAn(nhanVienDuAnDTO);
        sessionStatus.setComplete();
        SessionMessages.add(actionRequest, "form-success");

        actionResponse.setRenderParameter("action", "");
        actionResponse.setRenderParameter("id", String.valueOf(nhanVienDuAnDTO.getDuAn().getId()));
        actionResponse.setRenderParameter("name", String.valueOf(nhanVienDuAnDTO.getDuAn().getTenDuAn()));
    }

    @ActionMapping(params = "action=delete")
    public void xoa(ActionRequest request, ActionResponse response, SessionStatus sessionStatus,
                    @RequestParam(value = "id", defaultValue = "0") Integer id) throws Exception {

        NhanVienDuAnDTO nhanVienDuAnDTO = nhanVienDuAnService.findByNhanVienDuAnId(id);

        if (id > 0) {

            nhanVienDuAnService.deleteNhanVienDuAn(id);
        }

        sessionStatus.setComplete();
        SessionMessages.add(request, "form-success");
        response.setRenderParameter("action", "");
        response.setRenderParameter("id", String.valueOf(nhanVienDuAnDTO.getDuAn().getId()));
        response.setRenderParameter("name", nhanVienDuAnDTO.getDuAn().getTenDuAn());
    }
}