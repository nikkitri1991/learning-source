package com.adjecti.test.cron.portlet;

import com.adjecti.test.cron.constants.TestJobSchedulerPortletKeys;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;

import java.io.IOException;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
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
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=TestJobScheduler",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + TestJobSchedulerPortletKeys.TESTJOBSCHEDULER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class TestJobSchedulerPortlet extends MVCPortlet {
	@Reference
	private TriggerFactory _triggerFactory;
	@Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse)
        throws IOException, PortletException {
        super.render(renderRequest, renderResponse);
    }
	
	@Override
    public void processAction(ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException {
		Trigger trigger = _triggerFactory.createTrigger(
				TestJobSchedulerPortlet.class.getName(),
				TestJobSchedulerPortlet.class.getName(),
                new Date(),
                null,
                "0 30 16 * * ?" 
        );
		System.out.println(trigger);

        try {
			SchedulerEngineHelperUtil.schedule(
			        trigger,
			        StorageType.PERSISTED,
			        TestJobSchedulerPortlet.class.getName(),
			        DestinationNames.SCHEDULER_DISPATCH,
			        null,
			        0
			);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
        sendRedirect(actionRequest, actionResponse);

    }
	}

