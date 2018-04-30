package amogh.org.meettheteam.presenter;

import java.util.List;

import amogh.org.meettheteam.model.Person;
import amogh.org.meettheteam.ui.MeetTheTeamView;

/**
 * Created by Amogh on 1/29/2018.
 */

public interface MeetTheTeamPresenter {

    void setView(MeetTheTeamView view);

    void getTeamMembers(String json);

    int getGodDrawable(String id);
}
