package com.adjecti.test.portlet;

import com.liferay.dispatch.executor.BaseDispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutorOutput;
import com.liferay.dispatch.model.DispatchTrigger;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.service.component.annotations.Component;

@Component(
		property = {
			"dispatch.task.executor.name=" +DemoDispatchTaskExecuter.key, 
			"dispatch.task.executor.type="+DemoDispatchTaskExecuter.key
		},
		service = DispatchTaskExecutor.class
	)
public class DemoDispatchTaskExecuter extends BaseDispatchTaskExecutor {
	
	

	@Override
	public String getName() {
		return key;
	}

	private static final Log _log = LogFactoryUtil.getLog(
			DemoDispatchTaskExecuter.class);

	@Override
	public void doExecute(DispatchTrigger dispatchTrigger, DispatchTaskExecutorOutput dispatchTaskExecutorOutput)
			throws Exception {
		System.out.println("DemoDispatchTaskExecuter :::::  doExecute");
		
	}
	public static final String key="DemoDispatchTaskExecuter";


}
