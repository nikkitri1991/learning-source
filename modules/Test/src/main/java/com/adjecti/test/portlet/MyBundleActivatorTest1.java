package  com.adjecti.test.portlet;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.scheduler.*;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.*;

import java.util.Calendar;
import java.util.Date;

@Component(
    immediate = true
)
public class MyBundleActivatorTest1 implements BundleActivator {

    @Reference
    private SchedulerEngine _schedulerEngine;

    private Trigger _trigger;
    private MessageListener _messageListener;
    private ServiceRegistration<MessageListener> _messageListenerRegistration;

    @Activate
    protected void activate(BundleContext bundleContext) throws SchedulerException {
        _messageListener = new SchedulerMessageListenerTEst();

        _messageListenerRegistration = bundleContext.registerService(
            MessageListener.class, _messageListener, null);
       
        // Create the start date object for the trigger
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 17); // Set the hour to 5 PM
        calendar.set(Calendar.MINUTE, 45);
        calendar.set(Calendar.SECOND, 0);
        Date startDate = calendar.getTime();

        // Set up the cron expression for running the job daily at 5 PM
        String cronExpression = "0 45 17 * * ?"; // Run at 5 PM every day

        _trigger = TriggerFactoryUtil.createTrigger(
            getClass().getName(), getClass().getName(),
            startDate, null, cronExpression);

        _schedulerEngine.schedule(
	            _trigger, getClass().getName(),
	            DestinationNames.SCHEDULER_DISPATCH, new Message(),
	            StorageType.MEMORY_CLUSTERED);
    }

    @Deactivate
    protected void deactivate() throws SchedulerException {
        _schedulerEngine.unschedule(getClass().getName(),  StorageType.MEMORY_CLUSTERED);

        _messageListenerRegistration.unregister();
    }

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        activate(bundleContext);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        deactivate();
    }
    
    

    private class SchedulerMessageListenerTEst extends BaseMessageListener {

        @Override
        protected void doReceive(Message message) throws Exception {
            // Perform your logic here
            System.out.println("Printing a message at 5 PM");
        }
    }
}
