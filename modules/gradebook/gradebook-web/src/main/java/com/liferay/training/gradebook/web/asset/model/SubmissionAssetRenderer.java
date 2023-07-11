package com.liferay.training.gradebook.web.asset.model;

import com.liferay.asset.display.page.portlet.AssetDisplayPageFriendlyURLProvider;
import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import com.liferay.asset.util.AssetHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.training.gradebook.model.Submission;
import com.liferay.training.gradebook.web.constants.GradebookPortletKeys;
import com.liferay.training.gradebook.web.constants.MVCCommandNames;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubmissionAssetRenderer extends BaseJSPAssetRenderer<Submission>{

	public SubmissionAssetRenderer(Submission submission){
		this.submission=submission;
	}

	/**
	 * Provide All The Metadata Information For Asset Rendering Purpose
	 */
	@Override
	public Submission getAssetObject() {
		
		return submission;
	}

	@Override
	public long getGroupId() {
		
		return submission.getGroupId();
	}

	@Override
	public long getUserId() {
		return submission.getUserId();
	}

	@Override
	public String getUserName() {
		return submission.getUserName();
	}

	@Override
	public String getUuid() {
		return submission.getUuid();
	}

	@Override
	public String getClassName() {
		
		return Submission.class.getName();
	}

	@Override
	public long getClassPK() {
		
		return submission.getSubmissionId();
	}

	@Override
	public String getSummary(PortletRequest portletRequest, PortletResponse portletResponse) {
		ThemeDisplay themeDisplay = (ThemeDisplay) portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
		int abstractLength = AssetHelper.ASSET_ENTRY_ABSTRACT_LENGTH;
		
		String summary = HtmlUtil.stripHtml(
			StringUtil.shorten(submission.getComment(), abstractLength)
		);
		
		return summary;
	}

	@Override
	public String getTitle(Locale locale) {
		
		return submission.getSubmissionText();
	}

	@Override
	public String getJspPath(HttpServletRequest httpServletRequest, String template) {
		if(template.contains(TEMPLATE_ABSTRACT) || template.contains(TEMPLATE_FULL_CONTENT)) {
			return "/asset/" + template + ".jsp";
		}
		return null;
	}
	

	/**
	 * For Including The Custom Entity For The Asset Hierarchy Purpose
	 */
	 @Override
	public boolean include(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String template) throws Exception {
		 httpServletRequest.setAttribute("submission", submission);
		 return super.include(httpServletRequest, httpServletResponse, template);
	}
	
	// Setting Up The Asset Page Friendly URL
	public void setAssetDisplayPageFriendlyURLProvider(AssetDisplayPageFriendlyURLProvider assetDisplayPageFriendlyURLProvider) {
		this.assetDisplayPageFriendlyURLProvider = assetDisplayPageFriendlyURLProvider;
	}
	

	/**
	 *  Association With View's Appropriately
	 */
	@Override
	public String getURLView(LiferayPortletResponse liferayPortletResponse, WindowState windowState) throws Exception {
		return super.getURLView(liferayPortletResponse, windowState);
	}

	/*
	 * @Override public String getURLViewInContext(LiferayPortletRequest
	 * liferayPortletRequest, LiferayPortletResponse liferayPortletResponse, String
	 * noSuchEntryRedirect) throws Exception {
	 * if(assetDisplayPageFriendlyURLProvider != null) { ThemeDisplay themeDisplay =
	 * (ThemeDisplay) liferayPortletRequest.getAttribute(WebKeys.THEME_DISPLAY);
	 * String friendlyURL =
	 * assetDisplayPageFriendlyURLProvider.getFriendlyURL(getClassName(),
	 * getClassPK(), themeDisplay);
	 * 
	 * if(Validator.isNotNull(friendlyURL)) { return friendlyURL; } }
	 * 
	 * try { long plid = PortalUtil.getPlidFromPortletId(submission.getGroupId(),
	 * GradebookPortletKeys.PORTLET_NAME); PortletURL portletURL;
	 * 
	 * if(plid == LayoutConstants.DEFAULT_PLID) { portletURL =
	 * liferayPortletResponse.createLiferayPortletURL(getControlPanelPlid(
	 * liferayPortletRequest), GradebookPortletKeys.PORTLET_NAME,
	 * PortletRequest.RENDER_PHASE); } else { portletURL =
	 * PortletURLFactoryUtil.getPortletURLFactory().create(liferayPortletRequest,
	 * GradebookPortletKeys.PORTLET_NAME, PortletRequest.RENDER_PHASE); }
	 * 
	 * // Setup Parameter Details For Viewing Purpose
	 * portletURL.setParameter("mvcRenderCommandName",
	 * MVCCommandNames.VIEW_ASSIGNMENT); portletURL.setParameter("assignmentId",
	 * String.valueOf(submission.get);
	 * 
	 * String currentURL = PortalUtil.getCurrentURL(liferayPortletRequest);
	 * portletURL.setParameter("redirect", currentURL);
	 * 
	 * return portletURL.toString(); } catch (PortalException pe) { } catch
	 * (SystemException se) { } return null; }
	 */
	private Submission submission;
	private AssetDisplayPageFriendlyURLProvider assetDisplayPageFriendlyURLProvider;
	
	
}