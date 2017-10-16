package com.myitsolver.codifyapp.messagess;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.myitsolver.codifyapp.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private final CopyOnWriteArrayList<Message> mDataset;
    private View v;
    private SimpleDateFormat dateFormat;

    private static final int OTHER = 0;
    private static final int MAIN = 1;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView message;
        private TextView date;
        private ImageView profilePicture;


        ViewHolder(final View v) {
            super(v);
            message = (TextView) v.findViewById(R.id.message);
            date = (TextView) v.findViewById(R.id.date);
            profilePicture = (ImageView) v.findViewById(R.id.image);

        }
    }


    public MessageAdapter(CopyOnWriteArrayList<Message> data) {

        mDataset = data;

        dateFormat = new SimpleDateFormat("HH:mm");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == MAIN) {
            this.v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_main, parent, false);
        } else {
            this.v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_other, parent, false);
        }
        return new ViewHolder(v);
    }

    @Override
    public int getItemViewType(int position) {
        if (mDataset.get(position).isSelf()) {
            return MAIN;
        }
        return OTHER;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        Message c = mDataset.get(position);
        if (c == null) {
            return;
        }
        holder.message.setText(c.getMessage());
        holder.date.setText(dateFormat.format(c.getSendDate()));
        if (!c.isSelf()) {
            Picasso.with(holder.profilePicture.getContext()).load(c.getUrl()).into(holder.profilePicture);
        }
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}