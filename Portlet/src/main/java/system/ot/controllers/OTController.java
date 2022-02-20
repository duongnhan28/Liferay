package system.ot.controllers;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
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

import system.duan.dto.DuAnDTO;
import system.duan.services.DuAnService;
import system.nhanvien.dto.NhanVienDTO;
import system.nhanvien.services.NhanVienService;
import system.nhanvienduan.dto.NhanVienDuAnDTO;
import system.nhanvienduan.services.NhanVienDuAnService;
import system.ot.dto.OTDTO;
import system.ot.services.OTServices;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.validation.Valid;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.IntStream;

@Controller
@RequestMapping("VIEW")
@RequiredArgsConstructor
public class OTController {
    private final OTServices otServices;
    private final DuAnService duAnServices;
    private final NhanVienService nhanVienService;
    private final DuAnService duAnService;
    private final NhanVienDuAnService nhanVienDuAnService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @RenderMapping
    public String view(Model model, RenderRequest request,
                       @RequestParam(value = "id", defaultValue = "0", required = false) int duAnId) throws Exception {

        List<DuAnDTO> duAnDTOS = duAnServices.getDuAns();
        model.addAttribute("duAn", duAnDTOS);
        model.addAttribute("id", request.getParameter("id"));
        long userId = PortalUtil.getUserId(request);

        User user = PortalUtil.getUser(request);
        String from = user.getEmailAddress();
        model.addAttribute("fromUser", from);

        List<OTDTO> otdtos = otServices.getByDuAnId(duAnId);
        List<User> listNhanVien = new ArrayList<>();
        otdtos.forEach(otdto -> {
            otdto.getNhanVien().getUserId();
            try {
                User nhanVien = UserLocalServiceUtil.getUser(otdto.getNhanVien().getUserId());
                listNhanVien.add(nhanVien);
            } catch (PortalException e) {
                e.printStackTrace();
            }
        });
        model.addAttribute("email", listNhanVien);

        from = "duongnhan2611@gmail.com";
        model.addAttribute("emailGui", from);

        DuAnDTO duAnDTO = duAnService.getDuAn(duAnId);
        model.addAttribute("tenDuAn", duAnDTO.getTenDuAn());

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        List<Role> userRoles = RoleLocalServiceUtil.getUserRoles(themeDisplay.getUserId());
        List<Role> roles = themeDisplay.getUser().getRoles();
        model.addAttribute("userRoles", roles);

        for (Role role : userRoles) {
            if (role.getName().equals("Administrator")) {
                if (duAnId > 0) {
                    List<OTDTO> listView = otServices.getByDuAnId(duAnId);
                    model.addAttribute("listView", listView);
                    return "danhsach";
                }
                List<OTDTO> listView = otServices.getOTs();
                model.addAttribute("listView", listView);
                return "danhsach";
            } else if (role.getName().equals("Nhân viên")) {
                List<OTDTO> listView = otServices.getByDuAnId(duAnId);
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
                model.addAttribute("listView", listView);

                List<OTDTO> otdto = otServices.getOTByUserId(userId);
                nhanVienDuAns.forEach(nhanVienDuAnDTO -> {
                    if(nhanVienDuAnDTO.getVaiTro().getTenVaiTro().equals("DEV")){
                        nhanVienDuAnDTOS.add(nhanVienDuAnDTO);
                    }
                });

                nhanViens.forEach(nhanVien -> {
                    if (nhanVien.getVaiTro().getTenVaiTro().equals("DEV")){
                        List<NhanVienDuAnDTO> nhanVienDuAnDTOList = new ArrayList<>();
                        nhanVienDuAnDTOList.add(nhanVien);
                        model.addAttribute("nhanVienDuAnDTOList", nhanVienDuAnDTOList);
                    }

                });
                model.addAttribute("otByUser", otdto);
                model.addAttribute("nhanVienDuAns", nhanVienDuAnDTOS);
                return "danhsach";
            }
        }
        model.addAttribute("duAnId",request.getParameter(String.valueOf(duAnId)));
        List<OTDTO> otdto = otServices.getOTByUserId(userId);
        model.addAttribute("otByUser", otdto);
        return "danhsach";
    }

    @RenderMapping(params = "action=add")
    public String themMoi(RenderRequest request, Model model,
                          @RequestParam(value = "id", defaultValue = "0", required = false) Integer id,
                          @RequestParam(value = "duAnId", defaultValue = "0") Integer duAnId,
                          @RequestParam(required = false, defaultValue = "true") String validation) throws Exception {

        if (validation.equals("false")) {
            SessionErrors.add(request, "alert-error");
            return "/dangky";
        }
        User user = PortalUtil.getUser(request);
        String from = user.getEmailAddress();
        model.addAttribute("fromUser", from);

        List<NhanVienDuAnDTO> nhanVienDuAnDTOS = nhanVienDuAnService.findNhanVienByDuAn(duAnId);
        ArrayList<User> listNhanVien = new ArrayList<>();
        nhanVienDuAnDTOS.forEach(nhanVienDuAnDTO -> {
            nhanVienDuAnDTO.getNhanVien().getUserId();
            try {
                User nhanVien = UserLocalServiceUtil.getUser(nhanVienDuAnDTO.getNhanVien().getUserId());
                listNhanVien.add(nhanVien);
            } catch (PortalException e) {
                e.printStackTrace();
            }
        });
        model.addAttribute("emailPM", listNhanVien);
        String emailAdmin = PortalUtil.getPortalProperties().getProperty("admin.email.from.address");
        model.addAttribute("emailAdmin", emailAdmin);

        long userId = PortalUtil.getUserId(request);
        OTDTO item = new OTDTO();
        if (id > 0) {
            item = otServices.getOT(id);
        }
        NhanVienDTO nhanVien = nhanVienService.getNhanVienByUserId(userId);
        model.addAttribute("nhanVien", nhanVien);
        model.addAttribute("item", item);
        List<DuAnDTO> duAnDTOS = duAnService.getDuAnByUserId(userId);
        model.addAttribute("duAn", duAnDTOS);
        return "/dangky";
    }

    @ActionMapping(params = "action=add")
    public void themMoi(ActionRequest actionRequest, PortletRequest request, ActionResponse actionResponse, SessionStatus sessionStatus,
                        @ModelAttribute("item") @Valid OTDTO otdto, BindingResult bindingResult, Model model) throws Exception {
        long userId = PortalUtil.getUserId(request);
        if (bindingResult.hasErrors()) {
            List<DuAnDTO> duAnDTOS = duAnServices.getDuAns();
            model.addAttribute("duAn", duAnDTOS);
            NhanVienDTO nhanVien = nhanVienService.getNhanVienByUserId(userId);
            model.addAttribute("nhanVien", nhanVien);
            model.addAttribute("item", otdto);
            actionResponse.setRenderParameter("validation", "false");
            actionResponse.setRenderParameter("action", "add");
            actionResponse.setRenderParameter("duAnId", String.valueOf(otdto.getDuAn().getId()));
            return;
        }
        if (Validation.validate(otdto)) {
            otServices.saveOT(otdto);
            sessionStatus.setComplete();
            SessionMessages.add(actionRequest, "form-success");
            actionResponse.setRenderParameter("action", "");
            actionResponse.setRenderParameter("id", String.valueOf(otdto.getDuAn().getId()));
        } else {
            List<DuAnDTO> duAnDTOS = duAnServices.getDuAns();
            model.addAttribute("duAn", duAnDTOS);
            List<NhanVienDTO> nhanVienDTOS = nhanVienService.getNhanViens();
            model.addAttribute("nhanVien", nhanVienDTOS);
            model.addAttribute("item", otdto);
            actionResponse.setRenderParameter("validation", "false");
            actionResponse.setRenderParameter("action", "add");
            actionResponse.setRenderParameter("duAnId", String.valueOf(otdto.getDuAn().getId()));
        }
        sendMailCC(actionRequest,actionResponse,sessionStatus);
    }

    @ActionMapping(params = "action=delete")
    public void xoa(ActionRequest request, ActionResponse response, SessionStatus sessionStatus,
                    @RequestParam(value = "id", defaultValue = "0") Integer id) {
        OTDTO otdto = otServices.getOT(id);
        DuAnDTO duAnDTO = duAnService.getDuAn(otdto.getDuAn().getId());
        if (id > 0) {
            otServices.deleteOT(id);
        }
        sessionStatus.setComplete();
        SessionMessages.add(request, "form-success");
        response.setRenderParameter("action", "");
        response.setRenderParameter("id", String.valueOf(duAnDTO.getId()));


    }

    @ActionMapping(params = "action=sendMail")
    public void acpect(ActionRequest request, ActionResponse response, SessionStatus sessionStatus,Model model,
                       @RequestParam(value = "status", defaultValue = "0") int status,
                       @RequestParam(value = "otId", defaultValue = "0") Integer id) throws PortalException {
        OTDTO otdto = otServices.getOT(id);
        User user1 = UserLocalServiceUtil.getUser(otdto.getNhanVien().getUserId());
        String emailNhan = user1.getEmailAddress();
        model.addAttribute("emailReceive",emailNhan);
        DuAnDTO duAnDTO = duAnService.getDuAn(otdto.getDuAn().getId());

        if (status == 0) {
            otServices.acpectOT(id);
        } else {
            otServices.denyOT(id);
        }
        SessionMessages.add(request, "form-success");
        response.setRenderParameter("action", "");
        response.setRenderParameter("id", String.valueOf(duAnDTO.getId()));
        sendMail(request, response, sessionStatus);

    }
    @ActionMapping(params = "action=send")
    public static void sendMail(ActionRequest request, ActionResponse response, SessionStatus sessionStatus) {
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

        } catch (AddressException e) {
        }
    }

    @ActionMapping(params = "action=cc")
    public static void sendMailCC(ActionRequest request, ActionResponse response, SessionStatus sessionStatus) {

        String from = ParamUtil.getString(request, "from");
        String to = ParamUtil.getString(request, "to");
        String []ccEmail= ParamUtil.getStringValues(request,"cc");
        String subject = ParamUtil.getString(request, "subject");
        String body = ParamUtil.getString(request, "body");
        try {

            InternetAddress fromAddress = new InternetAddress(from);
            InternetAddress toAddress = new InternetAddress(to);
            InternetAddress []ccAddress = new InternetAddress[ccEmail.length];
            IntStream
                    .range(0, ccEmail.length).forEach(index -> {
                try {
                    ccAddress[index] = new InternetAddress(ccEmail[index]);
                } catch (AddressException e) {
                    e.printStackTrace();
                }
            });

            MailMessage mailMessagel = new MailMessage();
            mailMessagel.setFrom(fromAddress);
            mailMessagel.setTo(toAddress);
            mailMessagel.setCC(ccAddress);
            mailMessagel.setSubject(subject);
            mailMessagel.setBody(body);

            MailServiceUtil.sendEmail(mailMessagel);
            sessionStatus.setComplete();
            SessionMessages.add(request, "form-success");
            response.setRenderParameter("action", "");

        } catch (AddressException e) {
            e.printStackTrace();
        }
    }

}