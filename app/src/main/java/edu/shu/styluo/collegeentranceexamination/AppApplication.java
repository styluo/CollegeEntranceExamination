package edu.shu.styluo.collegeentranceexamination;

import android.app.Application;

/**
 * 自定义Application
 * @author  author: styluo
 * @Time date: 2017/4/17 22:14
 * @Email e-mail: shu_jiahuili@foxmail.com
 */

public class AppApplication extends Application {

    private static AppApplication mApplication;

    public static AppApplication getInstance(){
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }
}
