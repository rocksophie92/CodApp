package com.myitsolver.codifyapp.messagess;

import java.util.Date;
import java.util.Random;

/**
 * Created by Patrik on 2017. 08. 23..
 */

public class Message {
    private String message;
    private Date sendDate;
    private String url;
    private boolean isSelf;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isSelf() {
        return isSelf;
    }

    public void setSelf(boolean self) {
        isSelf = self;
    }

    public static Message getRandomMessage() {
        Message m = new Message();
        m.setMessage("Ez egy teszt üzenet");
        m.setSendDate(new Date());
        m.setSelf(new Random().nextBoolean());
        m.setUrl("https://asset1.modelmanagement.com/images/home/everyone_search.jpg");
        return m;
    }
}
