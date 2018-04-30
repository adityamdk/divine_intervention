package amogh.org.meettheteam.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import amogh.org.meettheteam.R;

/**
 * Created by Amogh on 1/30/2018.
 */

public class MeetTheTeamViewHolder extends RecyclerView.ViewHolder {

    private ViewGroup container;
    private TextView title;
    private ImageView image;

    public MeetTheTeamViewHolder(View itemView) {
        super(itemView);
        container = (ViewGroup) itemView.findViewById(R.id.list_item_team_container);
        title = (TextView) itemView.findViewById(R.id.list_item_title);
        image = (ImageView) itemView.findViewById(R.id.list_item_image);
    }

    public ViewGroup getContainer() {
        return container;
    }

    public TextView getTitleTextView() {
        return title;
    }

    public ImageView getImageView() {
        return image;
    }

}
