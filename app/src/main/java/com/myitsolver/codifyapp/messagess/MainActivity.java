package com.myitsolver.codifyapp.messagess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myitsolver.codifyapp.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String LATEST_MESSAGE ="latestMessage" ;
    @BindView(R.id.messageList)
    RecyclerView listView;
    @BindView(R.id.message)
    EditText message;
    @BindView(R.id.send)
    View send;

    MessageAdapter adapter;
    CopyOnWriteArrayList<Message> messages = new CopyOnWriteArrayList<>();
    private DatabaseReference refMessage;
    private ValueEventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        messages.addAll(getDummyMessages());
        refMessage = FirebaseDatabase.getInstance().getReference("Messages").child(LATEST_MESSAGE);

        loadData();
        listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("ValueChanged", "Firebase " + dataSnapshot.getValue(Message.class));
                messages.add(dataSnapshot.getValue(Message.class));


                adapter.notifyDataSetChanged();
                listView.scrollToPosition(messages.size() - 1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //error
            }
        };
        refMessage.addValueEventListener(listener);
    }

    @OnClick(R.id.send)
    public void send() {
        Message m = new Message();
        m.setSelf(true);
        m.setMessage(message.getText().toString());
        m.setSendDate(new Date());
        refMessage.setValue(m);

        message.setText("");


    }

    public void loadData() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);

        adapter = new MessageAdapter(messages);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(adapter);
    }

    public List<Message> getDummyMessages() {
        List<Message> ms = new ArrayList<>();
        ms.add(Message.getRandomMessage());
        ms.add(Message.getRandomMessage());
        ms.add(Message.getRandomMessage());
        ms.add(Message.getRandomMessage());
        ms.add(Message.getRandomMessage());
        ms.add(Message.getRandomMessage());
        ms.add(Message.getRandomMessage());
        return ms;
    }
}
