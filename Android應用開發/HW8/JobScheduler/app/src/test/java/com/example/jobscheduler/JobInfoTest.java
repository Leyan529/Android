package com.example.jobscheduler;

import android.app.Activity;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class JobInfoTest{

    private JobScheduler jobScheduler;
    private Activity activity;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(MainActivity.class);
        jobScheduler = (JobScheduler) activity.getSystemService(Context.JOB_SCHEDULER_SERVICE);
    }

    @Test
    public void testJobInfo() {
        List<JobInfo> jobList = jobScheduler.getAllPendingJobs();
        assertEquals (jobList.size(),1);
        for (JobInfo jb : jobList) {
                assertEquals(jb.getService().toString(),"ComponentInfo{com.example.jobscheduler/com.example.jobscheduler.MainActivity$NotificationJobService}");
                assertEquals(jb.getNetworkType(), 2);
                assertTrue(jb.isRequireCharging());
                assertTrue(jb.isRequireDeviceIdle());
                assertEquals(jb.getIntervalMillis(), 24 * 60 * 60 * 1000);
        }
    }
}
