package system.chitietcongtac.controllers;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
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
import system.chitietcongtac.dto.ChiTietCongTacDTO;
import system.chitietcongtac.services.ChiTietCongTacService;
import system.congtac.dto.CongTacDTO;
import system.congtac.services.CongTacService;
import system.duan.dto.DuAnDTO;
import system.nhanvien.dto.NhanVienDTO;
import system.nhanvien.services.NhanVienService;
import system.nhanvienduan.dto.NhanVienDuAnDTO;
import system.nhanvienduan.services.NhanVienDuAnService;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("VIEW")
@RequiredArgsConstructor
public class ChiTietCongTacController {

    private final ChiTietCongTacService chiTietCongTacService;
    private final NhanVienDuAnService nhanVienDuAnService;
    private final CongTacService congTacService;
    private final NhanVienService nhanVienService;

    @RenderMapping
    public String view(Model model, RenderRequest request, @RequestParam(value = "id") Integer id,
                       @RequestParam(value = "duAnId") Integer duAnId,
                       @RequestParam(value = "name") String name,
                       @RequestParam(value = "nhiemVu") String nhiemVu,
                       @RequestParam(value = "trangThai") String trangThai,
                       @RequestParam(value = "nhanVienId") Integer nhanVienId) throws Exception {

        long userId= PortalUtil.getUserId(request);
        model.addAttribute("userId", userId);

        User user = PortalUtil.getUser(request);
        String emailGui = user.getEmailAddress();
        CongTacDTO congTacDTO = congTacService.getCongTac(id);
        User nguoiPhuTrach = UserLocalServiceUtil.getUser(congTacDTO.getNhanVien().getUserId());
        String emailNhan = nguoiPhuTrach.getEmailAddress();
        // Hard code de tranh spam
        emailGui = "duongnhan2611@gmail.com";
        emailNhan = "phamngocanh011020@gmail.com";

        model.addAttribute("emailGui", emailGui);
        model.addAttribute("emailNhan", emailNhan);

        NhanVienDTO nhanVienDTO = nhanVienService.findByNhanVienUserId(userId);
        List<NhanVienDuAnDTO> nhanViens = nhanVienDuAnService.findNhanVienDuAnByNhanVien(nhanVienDTO.getId());
        List<NhanVienDuAnDTO> nhanVienDuAns = nhanVienDuAnService.findNhanVienByDuAn(duAnId);
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

        ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
        List<ChiTietCongTacDTO> listView = chiTietCongTacService.getByCongTac(id);
        List<Role> userRoles = themeDisplay.getUser().getRoles();

        model.addAttribute("nhanVienDuAns", nhanVienDuAnDTOS);
        model.addAttribute("userRoles", userRoles);
        model.addAttribute("id", request.getParameter("id"));
        model.addAttribute("tenDuAn", request.getParameter("name"));
        model.addAttribute("nhiemVu", request.getParameter("nhiemVu"));
        model.addAttribute("duAnId", request.getParameter("duAnId"));
        model.addAttribute("nhanVienId", request.getParameter("nhanVienId"));
        model.addAttribute("trangThai", request.getParameter("trangThai"));
        model.addAttribute("listView", listView);

        return "danhsach";
    }

    @RenderMapping(params = "action=add")

    public String themMoi(RenderRequest request, RenderResponse response, Model model,
                          @RequestParam(value = "id", defaultValue = "0") Integer id,
                          @RequestParam(value = "duAn.id") Integer duAnId,
                          @RequestParam(value = "name") String name,
                          @RequestParam(value = "nhiemVu") String nhiemVu,
                          @RequestParam(value = "congTac.id") Integer congTacId,
                          @RequestParam(value = "nhanVienId") Integer nhanVienId,
                          @RequestParam(value = "trangThai") String trangThai,
                          @RequestParam(required = false, defaultValue = "true") String validation) throws Exception {

        List<NhanVienDuAnDTO> listNhanVien = nhanVienDuAnService.findNhanVienByDuAn(duAnId);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("chiTietCongTac", new ChiTietCongTacDTO());
        model.addAttribute("tenDuAn", request.getParameter("name"));
        model.addAttribute("nhiemVu", request.getParameter("nhiemVu"));
        model.addAttribute("congTacId", request.getParameter("congTac.id"));
        model.addAttribute("duAnId", request.getParameter("duAn.id"));
        model.addAttribute("nhanVienId", request.getParameter("nhanVienId"));
        model.addAttribute("trangThai", request.getParameter("trangThai"));

        if (validation.equals("false")) {

            SessionErrors.add(request, "alert-error");

            return "/themmoi";
        }

        ChiTietCongTacDTO item = new ChiTietCongTacDTO();

        if (id > 0) {

            item = chiTietCongTacService.getById(id);
        }

        model.addAttribute("item", item);

        return "/themmoi";
    }

    @ActionMapping(params = "action=add")
    public void themMoi(ActionRequest actionRequest, ActionResponse actionResponse, SessionStatus sessionStatus,
                        @ModelAttribute("item") @Valid ChiTietCongTacDTO chiTietCongTacDTO, BindingResult bindingResult, Model model) throws Exception {

            long userId= PortalUtil.getUserId(actionRequest);
            CongTacDTO congTac = congTacService.getCongTac(chiTietCongTacDTO.getCongTac().getId());

            if(bindingResult.hasErrors()) {

                actionResponse.setRenderParameter("validation", "false");
                actionResponse.setRenderParameter("action", "add");
                actionResponse.setRenderParameter("congTac.id", String.valueOf(chiTietCongTacDTO.getCongTac().getId()));
                actionResponse.setRenderParameter("duAn.id", String.valueOf(congTac.getDuAn().getId()));
                actionResponse.setRenderParameter("nhanVienId", String.valueOf(userId));
                actionResponse.setRenderParameter("name", String.valueOf(congTac.getDuAn().getTenDuAn()));
                actionResponse.setRenderParameter("nhiemVu", String.valueOf(congTac.getNhiemVu()));
                actionResponse.setRenderParameter("trangThai", String.valueOf(congTac.getTrangThai()));

                return;
            }

            String[] tenChiPhis = actionRequest.getParameterValues("tenChiPhi");
            String[] donViTinhs = actionRequest.getParameterValues("donViTinh");
            String[] soLuongs = actionRequest.getParameterValues("soLuong");
            String[] donGias = actionRequest.getParameterValues("donGia");
            String[] thanhTiens = actionRequest.getParameterValues("thanhTien");
            String congTacId = actionRequest.getParameter("congTac.id");

            CongTacDTO congTacDTO = congTacService.findCongTacById(Integer.valueOf(congTacId));

            for (int i =0; i < tenChiPhis.length; i++){

                chiTietCongTacDTO.setCongTac(congTacDTO);
                chiTietCongTacDTO.setTenChiPhi(tenChiPhis[i]);
                chiTietCongTacDTO.setDonViTinh(donViTinhs[i]);
                chiTietCongTacDTO.setSoLuong(Integer.valueOf(soLuongs[i]));
                chiTietCongTacDTO.setDonGia(Double.valueOf(donGias[i]));
                chiTietCongTacDTO.setThanhTien(Double.valueOf(thanhTiens[i]));

                chiTietCongTacService.save(chiTietCongTacDTO);
            }

            sessionStatus.setComplete();
            SessionMessages.add(actionRequest, "form-success");
            actionResponse.setRenderParameter("action", "");
            actionResponse.setRenderParameter("id", String.valueOf(chiTietCongTacDTO.getCongTac().getId()));
            actionResponse.setRenderParameter("duAnId", String.valueOf(chiTietCongTacDTO.getCongTac().getDuAn().getId()));
            actionResponse.setRenderParameter("nhanVienId", String.valueOf(userId));
            actionResponse.setRenderParameter("name", String.valueOf(chiTietCongTacDTO.getCongTac().getDuAn().getTenDuAn()));
            actionResponse.setRenderParameter("nhiemVu", String.valueOf(chiTietCongTacDTO.getCongTac().getNhiemVu()));
            actionResponse.setRenderParameter("trangThai", String.valueOf(chiTietCongTacDTO.getCongTac().getTrangThai()));

    }

    @ActionMapping(params = "action=delete")
    public void xoa(ActionRequest request, ActionResponse response, SessionStatus sessionStatus,
                    @RequestParam(value = "id", defaultValue = "0") Integer id) {

        ChiTietCongTacDTO chiTietCongTacDTO = chiTietCongTacService.findById(id);

        if (id > 0) {

            chiTietCongTacService.delete(id);
        }

        sessionStatus.setComplete();
        SessionMessages.add(request, "form-success");
        response.setRenderParameter("action", "");
        response.setRenderParameter("id", String.valueOf(chiTietCongTacDTO.getCongTac().getId()));
        response.setRenderParameter("nhiemVu", String.valueOf(chiTietCongTacDTO.getCongTac().getNhiemVu()));
        response.setRenderParameter("duAnId", String.valueOf(chiTietCongTacDTO.getCongTac().getDuAn().getId()));
        response.setRenderParameter("nhanVienId", String.valueOf(chiTietCongTacDTO.getCongTac().getNhanVien().getUserId()));
        response.setRenderParameter("name", String.valueOf(chiTietCongTacDTO.getCongTac().getDuAn().getTenDuAn()));
        response.setRenderParameter("trangThai", String.valueOf(chiTietCongTacDTO.getCongTac().getTrangThai()));

    }

    @ActionMapping(params = "action=sendMail")
    public void duyetCongTac( ActionRequest request,  ActionResponse response, SessionStatus sessionStatus,
                          @RequestParam(value = "status", defaultValue = "0") int status,
                          @RequestParam(value = "congTacId", defaultValue = "0") Integer id) throws PortalException {

        CongTacDTO congTacDTO = congTacService.findCongTacById(id);

        if (status == 0) {
            if (id > 0) {
                chiTietCongTacService.dongY(id);
            }
        }
        else if (status == 2) {
            if (id > 0) {
                chiTietCongTacService.daTaoChiPhi(id);
            }
        } else {
            if (id > 0) {
                chiTietCongTacService.tuChoi(id);
            }
        }
        String from = ParamUtil.getString(request, "from");
        String to = ParamUtil.getString(request, "to");
        String subject = ParamUtil.getString(request, "subject");
        String body = ParamUtil.getString(request, "body");

        try {
            InternetAddress fromAddress = new InternetAddress(from);
            InternetAddress toAddress = new InternetAddress(to);

            MailMessage mailMessagel = new MailMessage();
            mailMessagel.setFrom(fromAddress);
            mailMessagel.setTo(toAddress);
            mailMessagel.setSubject(subject);
            mailMessagel.setBody(body);

            MailServiceUtil.sendEmail(mailMessagel);
            sessionStatus.setComplete();
            SessionMessages.add(request, "form-success");
            response.setRenderParameter("action", "");
            response.setRenderParameter("id", String.valueOf(congTacDTO.getId()));
            response.setRenderParameter("nhiemVu", String.valueOf(congTacDTO.getNhiemVu()));
            response.setRenderParameter("duAnId", String.valueOf(congTacDTO.getDuAn().getId()));
            response.setRenderParameter("nhanVienId", String.valueOf(congTacDTO.getNhanVien().getUserId()));
            response.setRenderParameter("name", String.valueOf(congTacDTO.getDuAn().getTenDuAn()));
            response.setRenderParameter("trangThai", String.valueOf(congTacDTO.getTrangThai()));
        } catch (AddressException e) {
        }

        User user = PortalUtil.getUser(request);
        user.getAddresses();

    }

    @ActionMapping(params = "action=addTienUng")
    public void tienUng(ActionRequest actionRequest, ActionResponse actionResponse, SessionStatus sessionStatus,
                        @ModelAttribute("item") CongTacDTO congTacDTO, BindingResult result, Model model) throws Exception {

            String congTacId = actionRequest.getParameter("id");
            String tienUng = actionRequest.getParameter("tienUng");
            congTacDTO.setId(Integer.valueOf(congTacId));
            congTacDTO.setTienUng(Double.valueOf(tienUng));
            congTacService.saveCongTac(congTacDTO);
    }

}