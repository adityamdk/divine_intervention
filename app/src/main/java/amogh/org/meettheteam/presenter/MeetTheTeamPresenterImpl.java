package amogh.org.meettheteam.presenter;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import amogh.org.meettheteam.R;
import amogh.org.meettheteam.app.MeetTheTeamApplication;
import amogh.org.meettheteam.model.Person;
import amogh.org.meettheteam.ui.MeetTheTeamView;

/**
 * Created by Amogh on 1/29/2018.
 */

public class MeetTheTeamPresenterImpl implements MeetTheTeamPresenter {

    private MeetTheTeamView view;

    public MeetTheTeamPresenterImpl(Context context) {
        ((MeetTheTeamApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void setView(MeetTheTeamView view) {
        this.view = view;
    }

    public void getTeamMembers(String json) {
        view.showLoading();

        ArrayList<Person> members = new ArrayList<>();

        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                Person person = getPersonDetailsFromJson(object);
                members.add(person);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        view.showTeam(members);

        view.hideLoading();
    }

    private Person getPersonDetailsFromJson(JSONObject object) {
        Person person = new Person();
        try {
            person.setId(object.getString("id"));
//            person.setAvatar(object.getString("avatar"));
            person.setBio(object.getString("bio"));
            person.setFirstName(object.getString("firstName"));
            person.setLastName(object.getString("lastName"));
            person.setTitle(object.getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public int getGodDrawable(String id) {
        switch(id) {
            case "0" : return R.drawable.ganesha640;
            case "1" : return R.drawable.shiva;
            case "2" : return R.drawable.vishnu;
            case "3" : return R.drawable.lakshmi;
            case "4" : return R.drawable.hanuman;
            case "5" : return R.drawable.krishna;
            case "6" : return R.drawable.saraswati;
            case "7" : return  R.drawable.durga;
        }
        return R.drawable.ganesha640;
    }

}
