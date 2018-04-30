package amogh.org.meettheteam.app;

import android.app.Application;

import amogh.org.meettheteam.dagger.AppComponent;
import amogh.org.meettheteam.dagger.AppModule;
import amogh.org.meettheteam.dagger.DaggerAppComponent;

/**
 * Created by Amogh on 1/29/2018.
 */

public class MeetTheTeamApplication extends Application {

    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = initDagger(this);
    }

    private AppComponent initDagger(MeetTheTeamApplication meetTheTeamApplication) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(meetTheTeamApplication))
                .build();
    }
}
