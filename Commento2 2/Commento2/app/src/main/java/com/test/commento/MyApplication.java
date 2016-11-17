package com.test.commento;

import android.app.Application;
import android.content.Context;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

import commentsender.CommentEmailSenderFactory;


@ReportsCrashes(
        reportSenderFactoryClasses = {CommentEmailSenderFactory.class}
)
public class MyApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        // The following line triggers the initialization of ACRA
        ACRA.init(this);
    }
}
