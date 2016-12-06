package dk.sdu.com.democracy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import dk.sdu.com.democracy.adapters.CommentAdapter;
import dk.sdu.com.democracy.adapters.CommentItem;
import dk.sdu.com.democracy.utils.Constants;

public class DebateActivity extends AppCompatActivity {

    private ListView listView;
    private TextView txtUser;
    private TextView txtComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debate);

        updateList();
    }

    private void updateList(){
        String comments = getIntent().getStringExtra(Constants.COMMENTS);
        try{
            ArrayList<CommentItem> arrayOfUsers = new ArrayList<CommentItem>();

            CommentAdapter adapter = new CommentAdapter(this, arrayOfUsers);

            ListView listView = (ListView) findViewById(R.id.listview);
            listView.setAdapter(adapter);

            // Appending comments
            JSONObject map = new JSONObject(comments);

            Iterator<?> users = map.keys();
            while(users.hasNext()){
                String user = (String)users.next();
                String comment = map.getString(user);

                int commentLength = 200;
                if (comment.length() > commentLength){
                    StringBuilder strippedComment = new StringBuilder();


                    for(int i = 0; i< commentLength; i++){
                        strippedComment.append(comment.charAt(i));
                    }

                    comment = strippedComment.toString() + "...\n\nLæs mere";
                }


                CommentItem item = new CommentItem(user+ ":", comment);

                adapter.add(item);
            }

            //listView.setAdapter(adapter);
        }
        catch (JSONException e){
            e.printStackTrace();
        }

    }
}
