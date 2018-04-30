package amogh.org.meettheteam.ui;

import java.util.List;

import amogh.org.meettheteam.model.Person;

/**
 * Created by Amogh on 1/29/2018.
 */

public interface MeetTheTeamView {

    void showLoading();

    void hideLoading();

    void showTeam(List<Person> membersList);

    void launchPersonDetail(Person person);
}
