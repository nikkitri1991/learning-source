/*
 * package com.adjecti.test.portlet;
 * 
 * import com.liferay.portal.kernel.log.Log; import
 * com.liferay.portal.kernel.log.LogFactoryUtil; import
 * com.liferay.portal.kernel.messaging.BaseMessageListener; import
 * com.liferay.portal.kernel.messaging.DestinationNames; import
 * com.liferay.portal.kernel.messaging.Message; import
 * com.liferay.portal.kernel.scheduler.SchedulerEngineHelper; import
 * com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil; import
 * com.liferay.portal.kernel.scheduler.SchedulerEntryImpl; import
 * com.liferay.portal.kernel.scheduler.SchedulerException; import
 * com.liferay.portal.kernel.scheduler.Trigger; import
 * com.liferay.portal.kernel.scheduler.TriggerFactory; import
 * com.liferay.portal.kernel.util.GetterUtil;
 * 
 * import java.util.Date; import java.util.Map;
 * 
 * import org.osgi.service.component.annotations.Activate; import
 * org.osgi.service.component.annotations.Component; import
 * org.osgi.service.component.annotations.Deactivate; import
 * org.osgi.service.component.annotations.Modified; import
 * org.osgi.service.component.annotations.Reference;
 * 
 * //@Component(immediate = true, property = { //
 * "cron.expression= 0 0/2 1/1 1/1 * ?" // }, // service =FirstScheduler.class
 * // )
 * 
 * public class FirstScheduler extends BaseMessageListener{ private
 * SchedulerEntryImpl _schedulerEntryImpl; private Log log =
 * LogFactoryUtil.getLog(FirstScheduler.class);
 * 
 * @Override protected void doReceive(Message message) throws Exception {
 * log.info("Scheduled task executed..."); System.out.println("start");
 * 
 * }
 * 
 * @Activate
 * 
 * @Modified protected void activate(Map<String, Object> properties) throws
 * SchedulerException { try { String cronExpression =
 * GetterUtil.getString(properties.get("cron.expression"), "cronExpression");
 * log.info(" cronExpression: " + cronExpression); String listenerClass =
 * getClass().getName(); log.info(" listenerClass: " + listenerClass); Trigger
 * jobTrigger = _triggerFactory.createTrigger(listenerClass, listenerClass, new
 * Date(), null, cronExpression); log.info(" jobTrigger: " + jobTrigger);
 * _schedulerEntryImpl = new SchedulerEntryImpl(listenerClass,jobTrigger);
 * log.info(" _schedulerEntryImpl: " + _schedulerEntryImpl);
 * _schedulerEngineHelper.register(this, _schedulerEntryImpl,
 * DestinationNames.SCHEDULER_DISPATCH); log.info(" register: " ); } catch
 * (Exception e) { log.error(e); }
 * 
 * }
 * 
 * @Deactivate protected void deactive() {
 * SchedulerEngineHelperUtil.unregister(this); }
 * 
 * @Reference private SchedulerEngineHelper _schedulerEngineHelper;
 * 
 * @Reference private TriggerFactory _triggerFactory;
 * 
 * }
 * 
 */