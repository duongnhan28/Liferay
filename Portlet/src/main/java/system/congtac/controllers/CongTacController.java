package system.congtac.controllers;

import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
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
import system.congtac.dto.CongTacDTO;
import system.congtac.services.CongTacService;
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
import java.util.stream.Collectors;

import static system.congtac.controllers.CongTacValidation.validate;

@Controller
@RequestMapping("VIEW")
@RequiredArgsConstructor
public class CongTacController {

    private final CongTacService congTacService;
    private final NhanVienDuAnService nhanVienDuAnService;
    private final DuAnService duAnService;
    private final NhanVienService nhanVienService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @RenderMapping
    public String view(Model model, RenderRequest request, @RequestParam(value = "id") Integer id, @RequestParam(value = "name") String name) throws Exception {

        ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
        long userId= PortalUtil.getUserId(request);
        long plid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), "chitietcongtac_WAR_quanlynoiboportlet");
        long plidNV = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), "nhanviencongtac_WAR_quanlynoiboportlet");

        LiferayPortletURL renderURL = PortletURLFactoryUtil.create(request,
                "chitietcongtac_WAR_quanlynoiboportlet", plid, PortletRequest.RENDER_PHASE);
        LiferayPortletURL renderNVURL = PortletURLFactoryUtil.create(request,
                "nhanviencongtac_WAR_quanlynoiboportlet", plidNV, PortletRequest.RENDER_PHASE);

        List<CongTacDTO> listView = congTacService.findCongTacByDuAn(id);
        List<Role> userRoles = themeDisplay.getUser().getRoles();

        NhanVienDTO nhanVienDTO = nhanVienService.findByNhanVienUserId(userId);
        List<NhanVienDuAnDTO> nhanViens = nhanVienDuAnService.findNhanVienDuAnByNhanVien(nhanVienDTO.getId());
        List<NhanVienDuAnDTO> nhanVienDuAns = nhanVienDuAnService.findNhanVienByDuAn(id);
        List<NhanVienDuAnDTO> nhanVienDuAnDTOS = new ArrayList<>();

        nhanVienDuAns.forEach(nhanVienDuAnDTO -> {
            if(nhanVienDuAnDTO.getVaiTro().getTenVaiTro().equals("PM")){
                nhanVienDuAnDTOS.add(nhanVienDuAnDTO);
            }
        });

        nhanViens.forEach(nhanVien -> {
            if (nhanVien.getVaiTro().getTenVaiTro().equals("PM")){
                List<NhanVienDuAnDTO> nhanVienDuAnDTOList = new ArrayList<>();
                nhanVienDuAnDTOList.add(nhanVien);
                model.addAttribute("nhanVienDuAnDTOList", nhanVienDuAnDTOList);
            }
        });

        model.addAttribute("nhanVienDuAns", nhanVienDuAnDTOS);
        model.addAttribute("chiTietCongTacUrl", renderURL.toString());
        model.addAttribute("nhanVienCongTacUrl", renderNVURL.toString());
        model.addAttribute("userRoles", userRoles);
        model.addAttribute("userId", userId);
        model.addAttribute("listView", listView);
        model.addAttribute("duAnId", request.getParameter("id"));
        model.addAttribute("tenDuAn", request.getParameter("name"));

        return "danhsach";
    }

    @RenderMapping(params = "action=add")

    public String themMoi(RenderRequest request, RenderResponse response, Model model,
                          @RequestParam(value = "id", defaultValue = "0") Integer id,
                          @RequestParam(value = "duAn.id") Integer duAnId,
                          @RequestParam(value = "name") String name,
                          @RequestParam(required = false, defaultValue = "true") String validation) throws Exception {

        List<NhanVienDuAnDTO> nhanViens = nhanVienDuAnService.findNhanVienByDuAn(duAnId);
        DuAnDTO duAnDTO = duAnService.getDuAn(duAnId);
        List<NhanVienDTO> nhanVienDTOS = new ArrayList<>();
        List<DuAnDTO> listDuAn = duAnService.getDuAns();

        nhanViens.forEach(nhanVien->{
            nhanVienDTOS.add(nhanVien.getNhanVien());
        });

        model.addAttribute("duAnId", request.getParameter("duAn.id"));
        model.addAttribute("tenDuAn", duAnDTO.getTenDuAn());
        model.addAttribute("listNhanVien", nhanVienDTOS);
        model.addAttribute("listDuAn", listDuAn);

        if (validation.equals("false")) {

            SessionErrors.add(request, "alert-error");

            return "/themmoi";
        }

        CongTacDTO item = new CongTacDTO();

        if (id > 0) {

            item = congTacService.getCongTac(id);
        }

        model.addAttribute("item", item);

        return "/themmoi";
    }

    @ActionMapping(params = "action=add")
    public void themMoi(ActionRequest actionRequest, ActionResponse actionResponse, SessionStatus sessionStatus,
                        @ModelAttribute("item") @Valid CongTacDTO congTacDTO, BindingResult bindingResult, Model model) throws Exception {

        DuAnDTO duAn = duAnService.getDuAn(congTacDTO.getDuAn().getId());

        if(bindingResult.hasErrors()) {

            List<NhanVienDuAnDTO> listNhanVien = nhanVienDuAnService.getNhanVienDuAns();
            List<DuAnDTO> listDuAn = duAnService.getDuAns();

            model.addAttribute("listNhanVien", listNhanVien);
            model.addAttribute("listDuAn", listDuAn);

            actionResponse.setRenderParameter("validation", "false");
            actionResponse.setRenderParameter("action", "add");
            actionResponse.setRenderParameter("duAn.id", String.valueOf(congTacDTO.getDuAn().getId()));
            actionResponse.setRenderParameter("name", String.valueOf(duAn.getTenDuAn()));

            return;
        }

        if (validate(congTacDTO)) {

            congTacService.saveCongTac(congTacDTO);
            sessionStatus.setComplete();
            SessionMessages.add(actionRequest, "form-success");

            actionResponse.setRenderParameter("action", "");
            actionResponse.setRenderParameter("id", String.valueOf(congTacDTO.getDuAn().getId()));
            actionResponse.setRenderParameter("name", String.valueOf(congTacDTO.getDuAn().getTenDuAn()));
        } else {

            List<NhanVienDuAnDTO> listNhanVien = nhanVienDuAnService.getNhanVienDuAns();
            List<DuAnDTO> listDuAn = duAnService.getDuAns();

            model.addAttribute("listNhanVien", listNhanVien);
            model.addAttribute("listDuAn", listDuAn);

            actionResponse.setRenderParameter("validation", "false");
            actionResponse.setRenderParameter("action", "add");
            actionResponse.setRenderParameter("duAn.id", String.valueOf(congTacDTO.getDuAn().getId()));
            actionResponse.setRenderParameter("name", String.valueOf(congTacDTO.getDuAn().getTenDuAn()));
        }

    }

    @ActionMapping(params = "action=delete")
    public void xoa(ActionRequest request, ActionResponse response, SessionStatus sessionStatus,
                    @RequestParam(value = "id", defaultValue = "0") Integer id) throws Exception {

        CongTacDTO congTacDTO = congTacService.findCongTacById(id);

        if (id > 0) {

            congTacService.deleteCongTac(id);
        }

        sessionStatus.setComplete();
        SessionMessages.add(request, "form-success");
        response.setRenderParameter("action", "");
        response.setRenderParameter("id", String.valueOf(congTacDTO.getDuAn().getId()));
        response.setRenderParameter("name", congTacDTO.getDuAn().getTenDuAn());
    }

}