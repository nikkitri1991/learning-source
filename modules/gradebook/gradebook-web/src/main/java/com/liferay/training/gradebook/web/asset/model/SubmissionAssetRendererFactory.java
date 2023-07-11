package com.liferay.training.gradebook.web.asset.model;

import com.liferay.asset.display.page.portlet.AssetDisplayPageFriendlyURLProvider;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.training.gradebook.model.Assignment;
import com.liferay.training.gradebook.model.Submission;
import com.liferay.training.gradebook.service.SubmissionLocalService;
import com.liferay.training.gradebook.web.constants.GradebookPortletKeys;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
@Component(immediate=true, property="javax.portlet.name="+ GradebookPortletKeys.PORTLET_NAME, service = AssetRendererFactory.class)
public class SubmissionAssetRendererFactory extends BaseAssetRendererFactory<Submission>{
	public static final String CLASS_NAME = Submission.class.getName();
	public static final String TYPE = "submission";
	
	public SubmissionAssetRendererFactory() {
		setClassName(CLASS_NAME);
		setLinkable(true);
		setPortletId(GradebookPortletKeys.PORTLET_NAME);
		setSearchable(true);
	}
	
	@Override
	public AssetRenderer<Submission> getAssetRenderer(long submissionId , int type) throws PortalException {
     Submission submission = submissionLocalService.getSubmission(submissionId);
		
		SubmissionAssetRenderer submissionAssetRenderer = new SubmissionAssetRenderer(submission);
		submissionAssetRenderer.setAssetDisplayPageFriendlyURLProvider(assetDisplayPageFriendlyURLProvider);
		submissionAssetRenderer.setAssetRendererType(type);
		submissionAssetRenderer.setServletContext(servletContext);
		
		return submissionAssetRenderer;
	}

	@Override
	public String getType() {
		
		return TYPE;
	}
	@Reference
	private AssetDisplayPageFriendlyURLProvider assetDisplayPageFriendlyURLProvider;
	@Reference(target = "(osgi.web.symbolicname=com.liferay.training.gradebook.web)")
	private ServletContext servletContext;
	@Reference
	private SubmissionLocalService submissionLocalService;

}
