package dk.sdu.com.democracy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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
import dk.sdu.com.democracy.utils.JsonUtil;

public class DebateActivity extends AppCompatActivity {
    private FloatingActionButton btnComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debate);

        updateList();

        btnComment = (FloatingActionButton)findViewById(R.id.btnComment);
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Opens activity, but does not store comment!
                Intent i = new Intent(getBaseContext(), DebateWriteCommentActivity.class);
                startActivity(i);
            }
        });
    }

    private void updateList(){
        String comments = getIntent().getStringExtra(Constants.COMMENTS);

        try{
            ArrayList<CommentItem> arrayOfUsers = new ArrayList<CommentItem>();

            CommentAdapter adapter = new CommentAdapter(this, arrayOfUsers);

            ListView listView = (ListView) findViewById(R.id.listview);
            listView.setAdapter(adapter);

            // Json upvotes
            String jsonUpvotes = JsonUtil.getJson(DebateActivity.this, Constants.JSON_COMMENTS_UPVOTES);
            JSONObject jsonUpvotesMap = new JSONObject(jsonUpvotes);

            // Appending comments
            JSONObject map = new JSONObject(comments);

            Iterator<?> users = map.keys();
            while(users.hasNext()){
                String user = (String)users.next();
                String comment = map.getString(user);
                int upvotes = jsonUpvotesMap.getInt(user);

                int commentLength = 200;
                if (comment.length() > commentLength){
                    StringBuilder strippedComment = new StringBuilder();


                    for(int i = 0; i< commentLength; i++){
                        strippedComment.append(comment.charAt(i));
                    }

                    comment = strippedComment.toString() + "...";
                }


                CommentItem item = new CommentItem(user+ ":", comment, upvotes, "Læs mere");

                adapter.add(item);
            }

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapter, View v, int position, long arg){
                    CommentItem item = (CommentItem)adapter.getItemAtPosition(position);

                    String name = item.name.substring(0, item.name.length() - 1);

                    String json = JsonUtil.getJson(DebateActivity.this, Constants.JSON_COMMENTS);

                    try{
                        JSONObject obj = new JSONObject(json);
                        String description = obj.getString(name);

                        Intent i = new Intent(DebateActivity.this, DebateItemActivity.class);
                        i.putExtra("name", name);
                        i.putExtra("description", description);
                        startActivity(i);
                    }
                    catch(JSONException e){
                        e.printStackTrace();
                    }
                }
            });
        }
        catch (JSONException e){
            e.printStackTrace();
        }

    }
}
