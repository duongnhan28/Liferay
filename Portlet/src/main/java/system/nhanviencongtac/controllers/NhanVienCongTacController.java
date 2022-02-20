package system.nhanviencongtac.controllers;

import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
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
import system.congtac.dto.CongTacDTO;
import system.congtac.services.CongTacService;
import system.nhanvien.dto.NhanVienDTO;
import system.nhanviencongtac.dto.NhanVienCongTacDTO;
import system.nhanviencongtac.services.NhanVienCongTacService;
import system.nhanvienduan.dto.NhanVienDuAnDTO;
import system.nhanvienduan.services.NhanVienDuAnService;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("VIEW")
@RequiredArgsConstructor
public class NhanVienCongTacController {

    private final NhanVienCongTacService nhanVienCongTacService;
    private final NhanVienDuAnService nhanVienDuAnService;
    private final CongTacService congTacService;

    @RenderMapping
    public String view(Model model, RenderRequest request, @RequestParam(value = "id") Integer id,
                       @RequestParam(value = "duAnId") Integer duAnId,
                       @RequestParam(value = "nhiemVu") String nhiemVu,
                       @RequestParam(value = "nhanVienId") Integer nhanVienId,
                       @RequestParam(value = "name") String name) throws Exception {

        long userId= PortalUtil.getUserId(request);
        model.addAttribute("userId", userId);

        ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
        List<NhanVienCongTacDTO> listView = nhanVienCongTacService.getbByCongTac(id);
        List<Role> roles = themeDisplay.getUser().getRoles();

        model.addAttribute("userRoles", roles);
        model.addAttribute("id", request.getParameter("id"));
        model.addAttribute("tenDuAn", request.getParameter("name"));
        model.addAttribute("nhiemVu", request.getParameter("nhiemVu"));
        model.addAttribute("duAnId", request.getParameter("duAnId"));
        model.addAttribute("nhanVienId", request.getParameter("nhanVienId"));
        model.addAttribute("listView", listView);

        return "danhsach";
    }

    @RenderMapping(params = "action=add")

    public String themMoi(RenderRequest request, RenderResponse response, Model model,
                          @RequestParam(value = "id", defaultValue = "0") Integer id,
                          @RequestParam(value = "duAn.id") Integer duAnId,
                          @RequestParam(value = "congTac.id") Integer congTacId,
                          @RequestParam(value = "nhiemVu") String nhiemVu,
                          @RequestParam(value = "name") String name,
                          @RequestParam(value = "nhanVienId") Integer nhanVienId,
                          @RequestParam(required = false, defaultValue = "true") String validation) throws Exception {

        List<NhanVienCongTacDTO> listView = nhanVienCongTacService.getbByCongTac(congTacId);
        List<NhanVienDuAnDTO> nhanViens = nhanVienDuAnService.findNhanVienByDuAn(duAnId);
        List<NhanVienDTO> nhanVienDTOS = new ArrayList<>();
        List<NhanVienDTO> nhanVienCongTacs = new ArrayList<>();

        nhanViens.forEach(nhanVien->{
            nhanVienDTOS.add(nhanVien.getNhanVien());
        });
        listView.forEach(nhanVienCongTacDTO -> {
            nhanVienDTOS.remove(nhanVienCongTacDTO.getNhanVien());
            nhanVienCongTacs.add(nhanVienCongTacDTO.getNhanVien());
        });

        model.addAttribute("listNhanVien", nhanVienDTOS);
        model.addAttribute("listView", nhanVienCongTacs);

        if (validation.equals("false")) {

            SessionErrors.add(request, "alert-error");

            return "/themmoi";
        }

        NhanVienCongTacDTO item = new NhanVienCongTacDTO();

        if (id > 0) {

            item = nhanVienCongTacService.getById(id);
        }
        model.addAttribute("tenDuAn", request.getParameter("name"));
        model.addAttribute("congTacId", request.getParameter("congTac.id"));
        model.addAttribute("nhiemVu", request.getParameter("nhiemVu"));
        model.addAttribute("duAnId", request.getParameter("duAn.id"));
        model.addAttribute("nhanVienId", request.getParameter("nhanVienId"));
        model.addAttribute("item", item);

        return "/themmoi";
    }

    @ActionMapping(params = "action=add")
    public void themMoi(ActionRequest actionRequest, ActionResponse actionResponse, SessionStatus sessionStatus,
                        @ModelAttribute("item") NhanVienCongTacDTO nhanVienCongTacDTO,BindingResult result, Model model) throws Exception {

            long userId= PortalUtil.getUserId(actionRequest);
            String[] nhanVienIds = actionRequest.getParameterValues("nhanVien.id");
            String congTacId = actionRequest.getParameter("congTac.id");

            CongTacDTO congTacDTO = congTacService.findCongTacById(Integer.valueOf(congTacId));
            NhanVienDTO nhanVienDTO = new NhanVienDTO();


            for(String id:nhanVienIds) {

                nhanVienDTO.setId(Integer.valueOf(id));

                nhanVienCongTacDTO.setNhanVien(nhanVienDTO);
                nhanVienCongTacDTO.setCongTac(congTacDTO);

                nhanVienCongTacService.save(nhanVienCongTacDTO);
            }

        sessionStatus.setComplete();
        SessionMessages.add(actionRequest, "form-success");
        actionResponse.setRenderParameter("action", "");
        actionResponse.setRenderParameter("id", String.valueOf(nhanVienCongTacDTO.getCongTac().getId()));
        actionResponse.setRenderParameter("duAnId", String.valueOf(nhanVienCongTacDTO.getCongTac().getDuAn().getId()));
        actionResponse.setRenderParameter("nhanVienId", String.valueOf(userId));
        actionResponse.setRenderParameter("name", String.valueOf(nhanVienCongTacDTO.getCongTac().getDuAn().getTenDuAn()));
        actionResponse.setRenderParameter("nhiemVu", String.valueOf(nhanVienCongTacDTO.getCongTac().getNhiemVu()));

    }

    @ActionMapping(params = "action=delete")
    public void xoa(ActionRequest request, ActionResponse response, SessionStatus sessionStatus,
                    @RequestParam(value = "id", defaultValue = "0") Integer id) {

        NhanVienCongTacDTO nhanVienCongTacDTO = nhanVienCongTacService.findById(id);

        if (id > 0) {

            nhanVienCongTacService.delete(id);
        }

        sessionStatus.setComplete();
        SessionMessages.add(request, "form-success");
        response.setRenderParameter("action", "");
        response.setRenderParameter("id", String.valueOf(nhanVienCongTacDTO.getCongTac().getId()));
        response.setRenderParameter("nhiemVu", String.valueOf(nhanVienCongTacDTO.getCongTac().getNhiemVu()));
        response.setRenderParameter("duAnId", String.valueOf(nhanVienCongTacDTO.getCongTac().getDuAn().getId()));
        response.setRenderParameter("nhanVienId", String.valueOf(nhanVienCongTacDTO.getCongTac().getNhanVien().getUserId()));
        response.setRenderParameter("name", String.valueOf(nhanVienCongTacDTO.getCongTac().getDuAn().getTenDuAn()));
    }

}