package amogh.org.meettheteam.dagger;

import android.content.Context;

import javax.inject.Singleton;

import amogh.org.meettheteam.presenter.MeetTheTeamPresenter;
import amogh.org.meettheteam.presenter.MeetTheTeamPresenterImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Amogh on 1/29/2018.
 */

@Module
class PresenterModule {

    @Provides
    @Singleton
    MeetTheTeamPresenter provideFoodzPresenter(Context context) {
        return new MeetTheTeamPresenterImpl(context);
    }
}
