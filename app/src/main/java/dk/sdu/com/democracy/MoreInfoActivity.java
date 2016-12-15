package dk.sdu.com.democracy;

import android.app.Activity;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.text.Html;
import android.widget.TextView;

import dk.sdu.com.democracy.utils.Constants;
import dk.sdu.com.democracy.utils.JsonUtil;

/**
 * Created by almir on 12/15/16.
 */

public class MoreInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moreinfo);
        TextView textView = (TextView) findViewById(R.id.txtMoreInfo2);
        textView.setText(Html.fromHtml(JsonUtil.getJson(this,Constants.HTML_MOREINFO)));
    }
}