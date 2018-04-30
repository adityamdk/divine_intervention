package amogh.org.meettheteam.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import amogh.org.meettheteam.R;
import amogh.org.meettheteam.app.MeetTheTeamApplication;
import amogh.org.meettheteam.model.Person;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonDetailActivity extends AppCompatActivity {

    public static final String INDIVIDUAL_PERSON_ID = "INDIVIDUAL_PERSON_ID";

    public static void launch(Context context, Person person) {
        Intent intent = new Intent(context, PersonDetailActivity.class);
        intent.putExtra(INDIVIDUAL_PERSON_ID, person);
        context.startActivity(intent);
    }

    @BindView(R.id.person_detail_title)
    TextView personDetailTitle;

    @BindView(R.id.person_detail_image)
    ImageView personDetailImage;

    @BindView(R.id.person_detail_bio)
    WebView personDetailBio;

    @BindView(R.id.person_detail_progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);

        ((MeetTheTeamApplication) getApplication()).getAppComponent().inject(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ButterKnife.bind(this);

        personDetailBio.setVerticalScrollBarEnabled(true);

        Person person = getIntent().getParcelableExtra(INDIVIDUAL_PERSON_ID);

        showLoading();
        showPersonDetails(person);
        hideLoading();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    public void showPersonDetails(Person person) {
        String title = person.getTitle();
        String first_name = person.getFirstName();
        String last_name = person.getLastName();
        String bio = person.getBio();

        personDetailTitle.setText(title);

        String bioHtml = makeHtmlFromString(bio);
        personDetailBio.loadData(bioHtml, "text/html", "utf-8");

        int resId = getGodDrawable(person.getId());

        Picasso.with(this).load(resId).fit().into(personDetailImage);
    }

    public String makeHtmlFromString(String text) {
        String textHtml;
        textHtml = "<html><body>";

        // Append paragraphs in text
        String[] paragraphs = text.split("\n\n");
        for (String p : paragraphs) {
            textHtml += "<p align=\"justify\">" + "<i>" + p + "</i>" + "</p>";
        }

        textHtml += "</body></html>";
        return textHtml;
    }

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
