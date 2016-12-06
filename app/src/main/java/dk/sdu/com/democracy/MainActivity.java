package dk.sdu.com.democracy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import dk.sdu.com.democracy.utils.Constants;

public class MainActivity extends AppCompatActivity {
    private TextView txtTitle;
    private TextView txtSubtitle;
    private TextView txtSubdescription;

    private Button comments;
    private Button statistics;
    private Button similarProposals;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtSubtitle = (TextView) findViewById(R.id.txtSubtitle);
        txtSubdescription = (TextView) findViewById(R.id.txtSubdescription);

        comments = (Button)findViewById(R.id.btnDebate);
        statistics = (Button)findViewById(R.id.btnStatistics);
        similarProposals = (Button)findViewById(R.id.btnSimilarProposals);

        addListeners();

        String json = getJson("lawproposals.json");
        updateUI(json);
    }

    private void addListeners(){
        comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), DebateActivity.class);
                i.putExtra(Constants.COMMENTS, getJson("comments.json"));
                i.putExtra(Constants.ID, id);
                startActivity(i);
            }
        });

        statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        similarProposals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private String getJson(String name) {
        String json = null;
        try {
            InputStream is = getAssets().open(name);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
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
