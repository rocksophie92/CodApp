package com.myitsolver.codifyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private static final String HELLO_WORLD = "helloWorld";
    private DatabaseReference mDatabase;
    private DatabaseReference refUser;
    private DatabaseReference message;
    private ValueEventListener listener;
    private DatabaseReference refMessage;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        subscribeForDataChanges();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unSubscribeForDataChanges();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);


        mDatabase = FirebaseDatabase.getInstance().getReference();

        refUser = FirebaseDatabase.getInstance().getReference("users");
        refMessage = FirebaseDatabase.getInstance().getReference("Messages").child(HELLO_WORLD);
        listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("ValueChanged", "Firebase " + dataSnapshot.getValue(User.class));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //error
            }
        };
        writeNewUser("Sophie", "Sample User", "emil@host.com");


setUserName("Sophie","Sophiee");    }



    public void subscribeForDataChanges() {
        refUser.addValueEventListener(listener);
    }

    public void unSubscribeForDataChanges() {
        refUser.removeEventListener(listener);
    }

    private void setUserName(String userId, String name) {
        mDatabase.child("users").child(userId).child("username").setValue(name);
    }
    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        mDatabase.child("users").child(userId).setValue(user);
    }



}
