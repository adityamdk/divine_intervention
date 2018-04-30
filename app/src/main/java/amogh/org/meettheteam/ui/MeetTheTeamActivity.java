package amogh.org.meettheteam.ui;

import android.graphics.Rect;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import amogh.org.meettheteam.R;
import amogh.org.meettheteam.app.Constants;
import amogh.org.meettheteam.app.MeetTheTeamApplication;
import amogh.org.meettheteam.model.Person;
import amogh.org.meettheteam.presenter.MeetTheTeamPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MeetTheTeamActivity extends AppCompatActivity implements MeetTheTeamView {

    @Inject
    MeetTheTeamPresenter meetTheTeamPresenter;

    @BindView(R.id.meet_the_team_recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.meet_the_team_progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meet_the_team);

        ((MeetTheTeamApplication) getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_view_grid_spacing);
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        meetTheTeamPresenter.setView(this);

        String json = readJsonFromAssets();

        meetTheTeamPresenter.getTeamMembers(json);

    }

    public String readJsonFromAssets() {
        String json = "";
        try {
            InputStream is = getAssets().open(Constants.JSON_FILE_NAME);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showTeam(List<Person> membersList) {
        recyclerView.setAdapter(new MeetTheTeamAdapter(membersList));
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void launchPersonDetail(Person person) {
        PersonDetailActivity.launch(this, person);
    }

    // Adapter for the RecyclerView
    private class MeetTheTeamAdapter extends RecyclerView.Adapter<MeetTheTeamViewHolder> {

        private List<Person> membersList;

        MeetTheTeamAdapter(List<Person> membersList) {
            this.membersList = membersList;
        }

        @Override
        public MeetTheTeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(MeetTheTeamActivity.this);
            return new MeetTheTeamViewHolder(inflater.inflate(R.layout.individual_person_item, parent, false));
        }

        @Override
        public void onBindViewHolder(MeetTheTeamViewHolder holder, int position) {
            final Person person = membersList.get(position);
            holder.getTitleTextView().setText(person.getTitle());
            int resId = meetTheTeamPresenter.getGodDrawable(person.getId());
            Picasso.with(holder.itemView.getContext()).load(resId).into(holder.getImageView());
            holder.getContainer().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    launchPersonDetail(person);
                }
            });
        }

        @Override
        public int getItemCount() {
            return membersList.size();
        }
    }

    // Decorator for the RecyclerView
    private static class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = space;
            } else {
                outRect.top = 0;
            }
        }
    }

}

