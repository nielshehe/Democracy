package dk.sdu.com.democracy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dk.sdu.com.democracy.R;

public class CommentAdapter extends ArrayAdapter<CommentItem> {
    public CommentAdapter(Context context, ArrayList<CommentItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommentItem comment = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.debate_rows, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.txtUser);
        TextView tvComment = (TextView) convertView.findViewById(R.id.txtComment);
        TextView tvUpvotes = (TextView) convertView.findViewById(R.id.txtUpvotes);
        TextView tvReadMore = (TextView) convertView.findViewById(R.id.txtReadMore);

        tvName.setText(comment.name);
        tvComment.setText(comment.comment);
        tvUpvotes.setText(comment.upvotes + " upvotes");
        tvReadMore.setText(comment.readMore);

        return convertView;
    }



    //public CommentItem getItem(int position){
    //    return getItem(position);
    //}
}
