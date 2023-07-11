package com.adjecti.dynamicQuery.portlet;

import com.adjecti.dynamicQuery.constants.DynamicQueryPortletKeys;
import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.workflow.kaleo.definition.Assignment;
import com.liferay.training.gradebook.service.AssignmentLocalService;
import com.liferay.training.gradebook.service.AssignmentLocalServiceUtil;
import com.liferay.training.gradebook.service.AssignmentService;
import com.liferay.training.gradebook.service.AssignmentServiceUtil;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Admin
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.list",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=DynamicQuery",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + DynamicQueryPortletKeys.DYNAMICQUERY,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class DynamicQueryPortlet extends MVCPortlet {
	private Log log = LogFactoryUtil.getLog(DynamicQueryPortlet.class);
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException{
		DynamicQuery userQuery = AssignmentLocalServiceUtil.dynamicQuery()
				.add(RestrictionsFactoryUtil.like("description", "%ass%"));
		List<Object> assignmentList = AssignmentLocalServiceUtil.dynamicQuery(userQuery);
		assignmentList.forEach(a -> System.out.println(a));
		renderRequest.setAttribute("assignmentList", assignmentList);
		super.doView(renderRequest, renderResponse);

	}
	
	@ProcessAction(name = "addAssignment")
	public void addAssignment(ActionRequest actionRequest, ActionResponse actionResponse) {
		String title = ParamUtil.getString(actionRequest, "title");
		String assignment = ParamUtil.getString(actionRequest, "assignment");
		String dueDate = ParamUtil.getString(actionRequest, "dueDate");
		long assignmentId = counterLocalService.increment(Assignment.class.getName());
		
		
	}
	  @Reference
	  private AssignmentService assignmentService;
	  @Reference
	  private CounterLocalService counterLocalService;
	 
}


