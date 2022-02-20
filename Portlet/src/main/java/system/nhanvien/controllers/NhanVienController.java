package system.nhanvien.controllers;

import com.liferay.portal.kernel.model.*;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.*;
import system.nhanvien.dto.NhanVienDTO;
import system.nhanvien.services.NhanVienService;
import javax.portlet.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("VIEW")
@RequiredArgsConstructor
public class NhanVienController {

	private final NhanVienService nhanVienService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
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

		long chiphiid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(),
				"chiphiphatsinh_WAR_quanlynoiboportlet");
		LiferayPortletURL chiphiURL = PortletURLFactoryUtil.create(request,
				"chiphiphatsinh_WAR_quanlynoiboportlet", chiphiid, PortletRequest.RENDER_PHASE);

		long ngaynghinhanvienid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(),
				"ngaynghinhanvien_WAR_quanlynoiboportlet");
		LiferayPortletURL ngaynghinhanvienURL = PortletURLFactoryUtil.create(request,
				"ngaynghinhanvien_WAR_quanlynoiboportlet", ngaynghinhanvienid, PortletRequest.RENDER_PHASE);

		for (Role role : userRoles) {
			if (role.getName().equals("Administrator")) {

				List<NhanVienDTO> listView = nhanVienService.getNhanViens();
				model.addAttribute("listView", UserLocalServiceUtil.getUsers(-1, -1));

				return "list";
			}
		}
		NhanVienDTO nhanVien = nhanVienService.getNhanVienByUserId(userId);
		model.addAttribute("nhanVien", nhanVien);

		model.addAttribute("chiphiphatsinhUrl", chiphiURL.toString());
		model.addAttribute("ngaynghinhanvienUrl", ngaynghinhanvienURL.toString());

		return "list";
	}

	@RenderMapping(params = "action=addNew")
	public String addNew(RenderRequest request, RenderResponse response, Model model,
						  @RequestParam(value = "id", defaultValue = "0") Integer id,
						  @RequestParam(value = "userId", defaultValue = "0") Integer userId,
						  @RequestParam(required = false, defaultValue = "true") String validation) throws Exception {

		if (validation.equals("false")) {
			SessionErrors.add(request, "alert-error");
			return "/form";
		}

		NhanVienDTO item = new NhanVienDTO();

		if (id > 0) {
			item = nhanVienService.getNhanVien(id);
		}

		NhanVienDTO nhanVienDTO = nhanVienService.findByNhanVienUserId(userId);

		if (nhanVienDTO != null) {
			item = nhanVienDTO;
		}

		item.setUserId(userId);
		model.addAttribute("item", item);

		return "form";
	}

	@ActionMapping(params = "action=addNew")
	public void addNew(ActionRequest actionRequest, ActionResponse actionResponse, SessionStatus sessionStatus,
					   @ModelAttribute("item") NhanVienDTO nhanVienDTO, BindingResult result, Model model) {

		nhanVienService.saveNhanVien(nhanVienDTO);
		sessionStatus.setComplete();
		SessionMessages.add(actionRequest, "form-success");
		actionResponse.setRenderParameter("action","");
	}

	@ActionMapping(params = "action=delete")
	public void delete(ActionRequest request, ActionResponse response, SessionStatus sessionStatus,
					@RequestParam(value = "id", defaultValue = "0") Integer id,
					   @RequestParam(value = "userId", defaultValue = "0") long userId) throws Exception{
		if (id > 0) {
			User user = UserLocalServiceUtil.getUser(userId);
			nhanVienService.deleteNhanVienByUserId(userId);
		}

		sessionStatus.setComplete();
		SessionMessages.add(request, "form-success");
		response.setRenderParameter("action","");
	}

}