package amogh.org.meettheteam.dagger;

import javax.inject.Singleton;

import amogh.org.meettheteam.ui.MeetTheTeamActivity;
import amogh.org.meettheteam.presenter.MeetTheTeamPresenterImpl;
import amogh.org.meettheteam.ui.PersonDetailActivity;
import dagger.Component;

/**
 * Created by Amogh on 1/29/2018.
 */

@Singleton
@Component(modules = {AppModule.class, PresenterModule.class})
public interface AppComponent {
    void inject(MeetTheTeamActivity target);

    void inject(MeetTheTeamPresenterImpl target);

    void inject(PersonDetailActivity target);

}
