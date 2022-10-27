package com.bigwinlab.b;

import static com.bigwinlab.b.AddAct.qazwsxedcrfv;
import static com.bigwinlab.b.MainActivity.qhxsgdfync;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.orhanobut.hawk.Hawk;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class SecondAddAc extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_add);
        new asyncFunc().execute();
    }


    public class asyncFunc extends AsyncTask<Void, Void, Void> {


        String result;
        String cAdder = Hawk.get(qazwsxedcrfv);
        String dAdder = Hawk.get(qhxsgdfync);
        String cor = "http://grandiose";
        String knil = "souls.xyz/go.php?to=1&";
        String oneis = "sub_id_1=";
        String namelnk = cor + knil+ oneis + cAdder;
        String deeplnk = cor + knil +oneis + dAdder;

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                Document doc;
                if (!cAdder.equals("null")){
                    doc = Jsoup.connect(namelnk).get();
                } else {
                    doc = Jsoup.connect(deeplnk).get();
                }
                result = doc.text();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Intent i1 = new Intent(getApplicationContext(), GrandioseGame.class);

            Intent i2 = new Intent(getApplicationContext(), Endless.class);
            if (result.equals("1g4c")) {
                startActivity(i1);
            } else {
                startActivity(i2);
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }

    }
}
