package com.example.alarmmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.ToggleButton;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowAlarmManager;

import java.util.Calendar;

@RunWith(RobolectricTestRunner.class)
public class AlarmManagerTest {
    ShadowAlarmManager shadowAlarmManager;
    AlarmManager alarmManager;
    Activity activity;
    Calendar cal = Calendar.getInstance();
    private static final int NOTIFICATION_ID = 0;

    @Before
    public void setUp() {
        alarmManager = (AlarmManager) RuntimeEnvironment.application.getSystemService(Context.ALARM_SERVICE);
        shadowAlarmManager = Shadows.shadowOf(alarmManager);
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void testAlarmManager() {
        Assert.assertNull(shadowAlarmManager.getNextScheduledAlarm());
        ToggleButton button = (ToggleButton)activity.findViewById(R.id.alarmToggle);
        button.performClick();
        assertScheduledAlarm(shadowAlarmManager.peekNextScheduledAlarm());
    }

    private void assertScheduledAlarm(ShadowAlarmManager.ScheduledAlarm scheduledAlarm){
        Intent notifyIntent = new Intent(activity, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(activity, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        cal.set(Calendar.HOUR_OF_DAY, 11);
        cal.set(Calendar.MINUTE, 11);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Assert.assertNotNull(scheduledAlarm);
        Assert.assertNotNull(scheduledAlarm.operation);
        Assert.assertSame(scheduledAlarm.operation,pendingIntent);
        Assert.assertEquals(scheduledAlarm.triggerAtTime,cal.getTimeInMillis());
    }
}
