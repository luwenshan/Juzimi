package com.lws.juzimi.common;

import android.content.Context;
import android.os.Environment;
import android.os.Process;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Wenshan.Lu on 2016/12/19.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static CrashHandler instance;

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        if (instance == null) {
            instance = new CrashHandler();
        }
        return instance;
    }

    public void init(Context context) {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 核心方法，当程序crash 会回调此方法， Throwable中存放这错误日志
     *
     * @param t
     * @param e
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        String logPath;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            logPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + File.separator
                    + "juzimi"
                    + File.separator
                    + "log";
            File file = new File(logPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            File logFile = new File(logPath + File.separator + "error.log");
            try {
                if (!logFile.exists()){
                    logFile.createNewFile();
                }
                FileWriter fw = new FileWriter(logPath + File.separator + "error.log", true);
                fw.write(new Date() + "\n");
                // 错误信息
                // 这里还可以加上当前的系统版本，机型型号 等等信息
                StackTraceElement[] stackTrace = e.getStackTrace();
                fw.write(e.getMessage() + "\n");
                for (StackTraceElement stackTraceElement : stackTrace) {
                    fw.write("file:" + stackTraceElement.getFileName()
                            + "class:" + stackTraceElement.getClassName()
                            + "method:" + stackTraceElement.getMethodName()
                            + "line:" + stackTraceElement.getLineNumber() + "\n");
                }
                fw.write("\n");
                fw.close();
            } catch (IOException e1) {
                Log.e("crash handler", "load file failed...", e1.getCause());
            }
        }
        e.printStackTrace();
        Process.killProcess(Process.myPid());
    }
}
