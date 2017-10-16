package com.myitsolver.codifyapp.messagess;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrik on 2017. 08. 24..
 */

public class CollectionTest {
    List<Message> messages1;
    List<Message> messages2;

    public void listTest() {
        Message m = new Message();

        messages1 = new ArrayList<>();
        messages2 = new ArrayList<>();

        messages1.add(m);
        m.setMessage("hello");
        messages1.get(0).setMessage("hello2");
        Log.d("", m.getMessage());

        messages2.add(m);
        messages2.add(m);
    }
}
