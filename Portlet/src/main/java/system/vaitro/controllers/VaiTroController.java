package system.vaitro.controllers;

import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.*;
import system.vaitro.dto.VaiTroDTO;
import system.vaitro.services.VaiTroService;

import javax.portlet.*;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("VIEW")
@RequiredArgsConstructor
public class VaiTroController {

    private final VaiTroService vaiTroService;

    @RenderMapping
    public String view(Model model, RenderRequest request) throws Exception {

        ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
        List<Role> roles = themeDisplay.getUser().getRoles();
        List<VaiTroDTO> listView = vaiTroService.getVaiTros();

        model.addAttribute("userRoles", roles);
        model.addAttribute("listView", listView);

        return "danhsach";
    }

    @RenderMapping(params = "action=add")
    public String themMoi(RenderRequest request, RenderResponse response, Model model,
                          @RequestParam(value = "id", defaultValue = "0") Integer id,
                          @RequestParam(required = false, defaultValue = "true") String validation) throws Exception {

        if (validation.equals("false")) {

            SessionErrors.add(request, "alert-error");

            return "/themmoi";
        }

        VaiTroDTO item = new VaiTroDTO();

        if (id > 0) {

            item = vaiTroService.getVaiTro(id);
        }

        model.addAttribute("item", item);

        return "/themmoi";
    }

    @ActionMapping(params = "action=add")
    public void themMoi(ActionRequest actionRequest, ActionResponse actionResponse, SessionStatus sessionStatus,
                        @ModelAttribute("item") @Valid VaiTroDTO chucVuDTO, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {


            actionResponse.setRenderParameter("validation", "false");
            actionResponse.setRenderParameter("action", "add");

            return;
        }

        vaiTroService.saveVaiTro(chucVuDTO);
        sessionStatus.setComplete();
        SessionMessages.add(actionRequest, "form-success");
        actionResponse.setRenderParameter("action", "");
    }

    @ActionMapping(params = "action=delete")
    public void xoa(ActionRequest request, ActionResponse response, SessionStatus sessionStatus,
                    @RequestParam(value = "id", defaultValue = "0") Integer id) {

        if (id > 0) {

            vaiTroService.deleteVaiTro(id);
        }

        sessionStatus.setComplete();
        SessionMessages.add(request, "form-success");
        response.setRenderParameter("action", "");
    }
}