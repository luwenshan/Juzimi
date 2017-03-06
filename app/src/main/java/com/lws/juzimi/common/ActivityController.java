package com.lws.juzimi.common;

import android.app.Activity;
import android.os.Process;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wenshan.Lu on 2016/12/19.
 */

public class ActivityController {
    private static final List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        synchronized (activities) {
            for (Activity activity : activities) {
                if (activity != null && !activity.isFinishing()) {
                    activity.finish();
                }
            }
        }
    }

    public static void exitApp() {
        finishAll();

        Process.killProcess(Process.myPid());
        System.exit(0);
    }
}
