package com.pcjh.assistant;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;

/**
 * Android 5.0+ 使用的 JobScheduler.
 * 运行在 :watch 子进程中.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class JobSchedulerService extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
        startService(new Intent(getApplication(), WorkService.class));
        jobFinished(params, false);
        return false;
    }
    private void onEnd(Intent rootIntent) {
        startService(new Intent(getApplication(), WorkService.class));
        startService(new Intent(getApplication(), WatchDogService.class));
    }
    @Override
    public boolean onStopJob(JobParameters params) {
        onEnd(null);
        return false;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        onEnd(rootIntent);
    }
}
