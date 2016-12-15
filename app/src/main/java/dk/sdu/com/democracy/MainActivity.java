package dk.sdu.com.democracy;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.v4.view.ScrollingView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import dk.sdu.com.democracy.utils.Constants;
import dk.sdu.com.democracy.utils.JsonUtil;

public class MainActivity extends AppCompatActivity {
    private TextView txtTitle;
    private TextView txtSubtitle;
    private TextView txtSubdescription;

    private ImageButton comments;
    private ImageButton statistics;

    private ImageButton like;
    private ImageButton dislike;
    private ImageButton neutral;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtSubtitle = (TextView) findViewById(R.id.txtSubtitle);
        txtSubdescription = (TextView) findViewById(R.id.txtSubdescription);

        comments = (ImageButton)findViewById(R.id.btnDebate);
        statistics = (ImageButton)findViewById(R.id.btnStatistics);

        like = (ImageButton)findViewById(R.id.btnLike);
        dislike = (ImageButton)findViewById(R.id.btnDislike);
        neutral = (ImageButton)findViewById(R.id.btnNeutral);


        addListeners();

        String json = JsonUtil.getJson(MainActivity.this, Constants.JSON_LAWPROPOSALS);
        updateUI(json);

        //ScrollView scrollTop = (ScrollView) findViewById(R.id.scrollViewTop);
        RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.relLayout);
        relLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), LawProposalActivity.class);
                i.putExtra(Constants.JSON_LAWPROPOSALS, JsonUtil.getJson(MainActivity.this, Constants.JSON_LAWPROPOSALS));
                startActivity(i);
            }
        });
    }

    private void addListeners(){
        comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), DebateActivity.class);
                i.putExtra(Constants.COMMENTS, JsonUtil.getJson(MainActivity.this, Constants.JSON_COMMENTS));
                i.putExtra(Constants.COMMENTS_ID, id);
                startActivity(i);
            }
        });

        statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), StatisticsActivity.class);
                //i.putExtra(Constants.COMMENTS, JsonUtil.getJson(MainActivity.this, Constants.JSON_COMMENTS));
                //i.putExtra(Constants.COMMENTS_ID, id);
                startActivity(i);
            }
        });

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeText("Du har stemt for ved dette lovforslag");
            }
        });

        dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeText("Du har stemt imod ved dette lovforslag");
            }
        });

        neutral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeText("Du er neutral ved dette lovforslag");
            }
        });

    }

    private void makeText(String text){
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    private void updateUI(String json){
        try {
            JSONObject obj = new JSONObject(json);

            txtTitle.setText(obj.getString("title"));
            txtSubtitle.setText(obj.getString("subtitle"));
            txtSubdescription.setText(obj.getString("subdescription"));

            id = obj.getString("id");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
