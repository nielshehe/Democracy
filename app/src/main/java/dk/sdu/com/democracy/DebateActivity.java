package dk.sdu.com.democracy;

import android.content.Intent;
import android.os.Bundle;
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

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapter, View v, int position, long arg){
                    CommentItem item = (CommentItem)adapter.getItemAtPosition(position);

                    String name = item.name.substring(0, item.name.length() - 1);

                    String json = JsonUtil.getJson(DebateActivity.this, Constants.JSON_COMMENTS);
                    System.out.println("json: "+json);

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
