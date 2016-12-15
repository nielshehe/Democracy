package dk.sdu.com.democracy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import dk.sdu.com.democracy.utils.Constants;
import dk.sdu.com.democracy.utils.JsonUtil;

public class MainActivity extends AppCompatActivity {
    private TextView txtTitle, txtSubtitle, txtSubdescription, txtMoreInfo;

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
        txtMoreInfo = (TextView) findViewById(R.id.txtMoreinfo);

        comments = (ImageButton)findViewById(R.id.btnDebate);
        statistics = (ImageButton)findViewById(R.id.btnStatistics);

        like = (ImageButton)findViewById(R.id.btnLike);
        dislike = (ImageButton)findViewById(R.id.btnDislike);
        neutral = (ImageButton)findViewById(R.id.btnNeutral);


        addListeners();

        String lawprop = JsonUtil.getJson(MainActivity.this, Constants.JSON_LAWPROPOSALS);
        String moreinfo = JsonUtil.getJson(MainActivity.this, Constants.HTML_MOREINFO);
        updateUI(lawprop, moreinfo);

        //ScrollView scrollTop = (ScrollView) findViewById(R.id.scrollViewTop);
        RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.relLayout);
        relLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("----------- clicked -----------");
                Intent i = new Intent(getBaseContext(), LawProposalActivity.class);
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
        txtMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),MoreInfoActivity.class));
            }
        });
    }

    private void makeText(String text){
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    private void updateUI(String lawproposal, String moreinfo){
        try {
            // The law proposal
            JSONObject law = new JSONObject(lawproposal);

            txtTitle.setText(law.getString("title"));
            txtSubtitle.setText(law.getString("subtitle"));
            txtSubdescription.setText(law.getString("subdescription"));

            id = law.getString("id");

            // The more info thing
            txtMoreInfo.setText(Html.fromHtml(moreinfo));


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
