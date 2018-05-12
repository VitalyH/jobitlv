package vitalyh.jobitlv;

import android.content.Context;

/**
 * Application object with a static method to access the application context
 * from a class which has no context.
 * In this app specifically it's the QueryUtils.
 * Solution was found: https://stackoverflow.com/questions/8238588/how-to-call-getresources-from-a-class-which-has-no-context
 */

public class App extends android.app.Application {
    private static App mApp = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

    public static Context context() {
        return mApp.getApplicationContext();
    }
}
