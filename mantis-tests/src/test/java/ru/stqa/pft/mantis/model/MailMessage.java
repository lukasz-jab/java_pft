package ru.stqa.pft.mantis.model;

/**
 * Created by luk on 2017-05-16.
 */
public class MailMessage {

    public String to;
    public String text;

    public MailMessage(String to, String text) {
        this.to = to;
        this.text = text;
    }
}
