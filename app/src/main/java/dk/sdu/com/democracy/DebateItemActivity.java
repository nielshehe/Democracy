package dk.sdu.com.democracy;

import android.content.Intent;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import dk.sdu.com.democracy.utils.Constants;

public class DebateItemActivity extends AppCompatActivity {

    private TextView user;
    private TextView description;

    private ImageButton imgBtnUpVote;

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debate_item);

        user = (TextView)findViewById(R.id.txtUser);
        description = (TextView)findViewById(R.id.txtComment);

        user.setText(getIntent().getStringExtra("name"));
        description.setText(getIntent().getStringExtra("description"));

        imgBtnUpVote = (ImageButton)findViewById(R.id.imgBtnDislike);
        imgBtnUpVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(count % 2 == 0){
                    imgBtnUpVote.setImageResource(R.drawable.debateliked);
                }

                else{
                    imgBtnUpVote.setImageResource(R.drawable.debatenotliked);
                }

                count++;
            }
        });
    }


}
