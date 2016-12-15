package dk.sdu.com.democracy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import dk.sdu.com.democracy.utils.Constants;
import dk.sdu.com.democracy.utils.JsonUtil;

public class LawProposalActivity extends AppCompatActivity {
    private TextView txtTitle;
    private TextView txtSubtitle;
    private TextView txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_proposal);

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtSubtitle = (TextView) findViewById(R.id.txtSubtitle);
        txtDescription = (TextView) findViewById(R.id.txtDescription);

        String jsonLawProposals = JsonUtil.getJson(LawProposalActivity.this, Constants.JSON_LAWPROPOSALS);
        updateUI(jsonLawProposals);

    }

    private void updateUI(String json){
        try {
            JSONObject obj = new JSONObject(json);

            txtTitle.setText(obj.getString("title"));
            txtSubtitle.setText(obj.getString("subtitle"));
            txtDescription.setText(obj.getString("fulldescription"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
