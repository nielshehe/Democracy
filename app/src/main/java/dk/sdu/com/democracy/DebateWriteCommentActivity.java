package dk.sdu.com.democracy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import dk.sdu.com.democracy.adapters.CommentAdapter;
import dk.sdu.com.democracy.adapters.CommentItem;
import dk.sdu.com.democracy.utils.Constants;
import dk.sdu.com.democracy.utils.JsonUtil;

public class DebateWriteCommentActivity extends AppCompatActivity {
    private FloatingActionButton btnComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_comment);
    }
}
