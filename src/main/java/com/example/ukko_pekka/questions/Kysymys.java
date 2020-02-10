package com.example.ukko_pekka.questions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ukko-Pekka on 9.11.2017.
 */

public class Kysymys {
    private String id;
    private String question;
    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Kysymys(JSONObject object){
        try {
            this.question = object.getString("question");
            this.id = object.getString("id");
        }catch (JSONException e){
            e.printStackTrace();
        }
    }


    public class Vastaus {
        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getQ_id() {
            return q_id;
        }

        public void setQ_id(String q_id) {
            this.q_id = q_id;
        }

        private String txt;
        private String id;
        private String q_id;

        public Vastaus (JSONObject obj){
            try {
                this.id = obj.getString("id");
                this.q_id = obj.getString("q_id");
                this.txt = obj.getString("txt");

            }catch (JSONException e){
                e.printStackTrace();
            }
        }
    }
}
