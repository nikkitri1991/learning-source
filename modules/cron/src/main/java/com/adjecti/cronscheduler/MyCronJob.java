/*
 * import com.liferay.portal.kernel.log.Log; import
 * com.liferay.portal.kernel.log.LogFactoryUtil; import
 * com.liferay.portal.kernel.messaging.BaseMessageListener; import
 * com.liferay.portal.kernel.messaging.DestinationNames; import
 * com.liferay.portal.kernel.messaging.Message; import
 * com.liferay.portal.kernel.scheduler.*; import
 * org.osgi.service.component.annotations.*;
 * 
 * import java.util.Date; import java.util.Map;
 * 
 * @Component( configurationPid = MyConfigurationKeys.MY_CONFIGURATION_ID,
 * //todo 1: - set configuration ID immediate = true, property = { }, service =
 * BaseMessageListener.class ) public class MyCronJob extends
 * BaseMessageListener {
 * 
 * private static final String CRON_EXPRESSION_DEFAULT = "0 * * * * ?"; private
 * static final boolean CRON_ENABLED_DEFAULT = false;
 * 
 * @Activate
 * 
 * @Modified protected void activate(Map<String, Object> properties) throws
 * SchedulerException { String cronExpresion = CRON_EXPRESSION_DEFAULT; boolean
 * cronEnabled = CRON_ENABLED_DEFAULT;
 * 
 * //todo 2: - use your configuration util MyConfiguration myConfiguration =
 * MyConfigurationUtil.getMyConfiguration(); if (myConfiguration != null) {
 * cronEnabled = myConfiguration.cronEnabled(); cronExpresion =
 * myConfiguration.cronExpression(); } if (cronEnabled) { //Cronjob enabled
 * String listenerClass = getClass().getName(); Trigger jobTrigger =
 * _triggerFactory.createTrigger(listenerClass, listenerClass, new Date(), null,
 * cronExpresion); _schedulerEntryImpl = new
 * SchedulerEntryImpl(getClass().getName(), jobTrigger); if (_initialized) {
 * deactivate(); } _schedulerEngineHelper.register(this, _schedulerEntryImpl,
 * DestinationNames.SCHEDULER_DISPATCH);
 * _log.debug("Scheduled task registered: " + cronExpresion); _initialized =
 * true; } else { //Cronjob disabled unregister(); } }
 * 
 * @Override protected void doReceive(Message message) throws Exception {
 * System.out.println("test"); // todo 3 - invoke your service }
 * 
 * deactivate: Called when OSGi is deactivating the component
 * 
 * @Deactivate protected void deactivate() { unregister(); }
 * 
 * private void unregister() { // if we previously were initialized if
 * (_initialized) { // un-schedule the job so it is cleaned up try {
 * _schedulerEngineHelper.unschedule(_schedulerEntryImpl, getStorageType()); }
 * catch (SchedulerException se) { if (_log.isWarnEnabled()) {
 * _log.warn("Unable to unschedule trigger", se); } } // unregister this
 * listener _schedulerEngineHelper.unregister(this); } // clear the initialized
 * flag _initialized = false; }
 * 
 * protected StorageType getStorageType() { if (_schedulerEntryImpl instanceof
 * StorageTypeAware) { return ((StorageTypeAware)
 * _schedulerEntryImpl).getStorageType(); } return StorageType.MEMORY_CLUSTERED;
 * }
 * 
 * 
 * private volatile boolean _initialized; private SchedulerEntryImpl
 * _schedulerEntryImpl = null;
 * 
 * @Reference private TriggerFactory _triggerFactory;
 * 
 * @Reference private SchedulerEngineHelper _schedulerEngineHelper;
 * 
 * @Reference private MyService _myService; // todo 4 - inject your service
 * 
 * private static final Log _log = LogFactoryUtil.getLog(MyCronJob.class); }
 */

/*
 * package com.adjecti.cronscheduler;
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
 * "cron.expression= 0 0/2 8-17 * * ?" // scheduler runs every 5 min. }, service
 * = MyCronJob.class ) public class MyCronJob extends BaseMessageListener{
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