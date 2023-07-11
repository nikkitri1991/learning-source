package com.adjecti.test.cronjob;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = SchedularDemo.class)

public class SchedularDemo extends BaseMessageListener {

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

	@Reference
	private TriggerFactory _triggerFactory;
	private SchedulerEntryImpl _schedulerEntryImpl;

	@Override
	protected void doReceive(Message message) throws Exception {
		System.out.println("start scheduler");
	}

	@Activate
  
  @Modified protected void activate(Map<String, Object> properties) throws
  ConfigurationException {
  System.out.println("+++++++++++++++++Activate+++++++++++++++++++++");
  //String cronExpression = "0 0/1 * 1/1 * ? *"; 
  String cronExpression ="0 * * ? * *"; // Run at 12 PM every day
  
  // Create the start date object for the trigger 
  Calendar calendar =Calendar.getInstance(); 
  calendar.set(Calendar.HOUR_OF_DAY, 19); 
  calendar.set(Calendar.MINUTE, 0); 
  calendar.set(Calendar.SECOND,0);
  calendar.set(Calendar.MILLISECOND, 0); 
  Date startDate =calendar.getTime();
 
  
  String listenerClass = getClass().getName(); 
  Trigger jobTrigger =_triggerFactory.createTrigger(listenerClass, listenerClass, startDate, null,
  cronExpression); _schedulerEntryImpl = new SchedulerEntryImpl(listenerClass,
  jobTrigger); _schedulerEngineHelper.register(this, _schedulerEntryImpl,
  DestinationNames.SCHEDULER_DISPATCH);
  System.out.println("Activate Method Running----"); }

	@Deactivate
	protected void deactive() {
		SchedulerEngineHelperUtil.unregister(this);
	}
}

/*
 * package com.adjecti.test.cronjob;
 * 
 * import com.liferay.portal.kernel.messaging.BaseMessageListener; import
 * com.liferay.portal.kernel.messaging.DestinationNames; import
 * com.liferay.portal.kernel.messaging.Message; import
 * com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil; import
 * com.liferay.portal.kernel.scheduler.SchedulerEntryImpl; import
 * com.liferay.portal.kernel.scheduler.SchedulerException; import
 * com.liferay.portal.kernel.scheduler.Trigger; import
 * com.liferay.portal.kernel.scheduler.TriggerFactoryUtil; import
 * com.liferay.portal.kernel.util.GetterUtil;
 * 
 * import java.util.Date; import java.util.Map;
 * 
 * import org.osgi.service.component.annotations.Activate; import
 * org.osgi.service.component.annotations.Component; import
 * org.osgi.service.component.annotations.Deactivate; import
 * org.osgi.service.component.annotations.Modified;
 * 
 * @Component( immediate = true, property = {
 * "cron.expression= 0 0/01 1/1 1/1 * ?" // scheduler runs every 5 min. },
 * service = SchedularDemo.class ) public class SchedularDemo extends
 * BaseMessageListener{
 * 
 * @Override protected void doReceive(Message message) throws Exception {
 * System.out.println("test-------------");
 * 
 * }
 * 
 * @Activate
 * 
 * @Modified protected void activate(Map<String, Object> properties) throws
 * SchedulerException {
 * 
 * try { String cronExpression =
 * GetterUtil.getString(properties.get("cron.expression"), "cronExpression");
 * System.out.println("cron");
 * 
 * String listenerClass = getClass().getName(); Trigger jobTrigger =
 * TriggerFactoryUtil.createTrigger(listenerClass, listenerClass, new Date(),
 * null, cronExpression);
 * 
 * SchedulerEntryImpl schedulerEntryImpl = new
 * SchedulerEntryImpl(listenerClass,jobTrigger);
 * 
 * 
 * SchedulerEngineHelperUtil.register(this, schedulerEntryImpl,
 * DestinationNames.SCHEDULER_DISPATCH);
 * 
 * } catch (Exception e) { System.out.println(e); } }
 * 
 * 
 * @Deactivate protected void deactive() {
 * SchedulerEngineHelperUtil.unregister(this); }
 * 
 * }
 */