package system.duan.controllers;

import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
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
import system.duan.dto.DuAnDTO;
import system.duan.services.DuAnService;
import system.nhanvien.dto.NhanVienDTO;
import system.nhanvien.services.NhanVienService;
import system.nhanvienduan.dto.NhanVienDuAnDTO;
import system.nhanvienduan.services.NhanVienDuAnService;

import javax.portlet.*;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("VIEW")
@RequiredArgsConstructor
public class DuAnController {

    private final DuAnService duAnService;
    private final NhanVienService nhanVienService;
    private final NhanVienDuAnService nhanVienDuAnService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @RenderMapping
    public String view(Model model, RenderRequest request) throws Exception {

        long userId= PortalUtil.getUserId(request);

        ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);

        long plid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), "nhanvienduan_WAR_quanlynoiboportlet");
        long plidCongTac = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), "congtac_WAR_quanlynoiboportlet");
        long plidOt = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), "ot_WAR_quanlynoiboportlet");
        LiferayPortletURL renderURL = PortletURLFactoryUtil.create(request,
                "nhanvienduan_WAR_quanlynoiboportlet", plid, PortletRequest.RENDER_PHASE);
        LiferayPortletURL renderCongTacURL = PortletURLFactoryUtil.create(request,
                "congtac_WAR_quanlynoiboportlet", plidCongTac, PortletRequest.RENDER_PHASE);
        LiferayPortletURL renderOTURL = PortletURLFactoryUtil.create(request,
                "ot_WAR_quanlynoiboportlet", plidOt, PortletRequest.RENDER_PHASE);



        List<Role> userRoles= RoleLocalServiceUtil.getUserRoles(themeDisplay.getUserId());
        List<Role> roles = themeDisplay.getUser().getRoles();

        model.addAttribute("userRoles", roles);
        model.addAttribute("nhanVienDuAnUrl", renderURL.toString());
        model.addAttribute("congTacUrl", renderCongTacURL.toString());
        model.addAttribute("oTURL", renderOTURL.toString());


        for (Role role : userRoles) {
            if (role.getName().equals("Administrator") || role.getName().equals("Kế toán")) {

                List<DuAnDTO> listView = duAnService.getDuAns();

                model.addAttribute("listView", listView);

                return "danhsach";
            }
        }

        NhanVienDTO nhanVienUserId = nhanVienService.findByNhanVienUserId(userId);
        List<NhanVienDuAnDTO> duAns = nhanVienDuAnService.findDuAnByNhanVien(nhanVienUserId.getId());
        List<DuAnDTO> duAnDTOS = new ArrayList<>();
        duAns.forEach(duAn->{
            duAnDTOS.add(duAn.getDuAn());

        });

        model.addAttribute("listView", duAnDTOS);

        return "danhsach";
    }

    @RenderMapping(params = "action=add")
    public String themMoi(RenderRequest request,@Valid Model model,
                          @RequestParam(required = false, value = "id", defaultValue = "0") Integer id,
                          @RequestParam(required = false, defaultValue = "true") String validation) throws Exception {

        List<NhanVienDTO> listNhanVien = nhanVienService.getNhanViens();
        model.addAttribute("listNhanVien", listNhanVien);

        if (validation.equals("false")) {
            SessionErrors.add(request, "alert-error");

            return "/themmoi";
        }

        DuAnDTO item = new DuAnDTO();

        if (id > 0) {
            item = duAnService.getDuAn(id);
        }

        model.addAttribute("item", item);

        return "/themmoi";
    }

    @ActionMapping(params = "action=add")
    public void themMoi(ActionRequest actionRequest, ActionResponse actionResponse, SessionStatus sessionStatus,
                         @ModelAttribute("item") @Valid DuAnDTO duAnDTO, BindingResult bindingResult, Model model) throws Exception {

         if(bindingResult.hasErrors()) {

             List<NhanVienDTO> listNhanVien = nhanVienService.getNhanViens();
             model.addAttribute("listNhanVien", listNhanVien);
             model.addAttribute("item", duAnDTO);
             model.addAttribute("id", duAnDTO.getId());

             actionResponse.setRenderParameter("validation", "false");
             actionResponse.setRenderParameter("action", "add");

             return;
         }

        if (DuAnValidation.validate(duAnDTO)) {
            duAnService.saveDuAn(duAnDTO);
            sessionStatus.setComplete();
            SessionMessages.add(actionRequest, "form-success");
            actionResponse.setRenderParameter("action", "");
        } else {

            List<NhanVienDTO> listNhanVien = nhanVienService.getNhanViens();
            model.addAttribute("listNhanVien", listNhanVien);
            model.addAttribute("item", duAnDTO);


            actionResponse.setRenderParameter("validation", "false");
            actionResponse.setRenderParameter("action", "add");
        }

    }

    @ActionMapping(params = "action=delete")
    public void xoa(ActionRequest request, ActionResponse response, SessionStatus sessionStatus,
                    @RequestParam(value = "id", defaultValue = "0") Integer id) {

        if (id > 0) {

            duAnService.deleteDuAn(id);
        }

        sessionStatus.setComplete();
        SessionMessages.add(request, "form-success");
        response.setRenderParameter("action", "");
    }
}