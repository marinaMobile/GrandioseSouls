package com.bigwinlab.b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.facebook.applinks.AppLinkData;
import com.orhanobut.hawk.Hawk;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MainActivity extends AppCompatActivity {

    public static final String qhxsgdfync = "dfB1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        metaLnk();
        new asyncFunc().execute();
    }

    public class asyncFunc extends AsyncTask<Void, Void, Void> {

        String result;
        String corelnk = "http://grandiose";
        String hfhs = "souls.xyz/apps.txt";
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc;
                doc = Jsoup.connect(corelnk+hfhs).get();
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

            Intent i2 = new Intent(getApplicationContext(), AddAct.class);
            switch(result){
                case "1":    startActivity(i2);
                    break;
                case "2":    startActivity(i1);
                    break;
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }


    }
    public void metaLnk() {

        AppLinkData.fetchDeferredAppLinkData(this,
                appLinkData -> {
                    if (appLinkData != null) {
                        String params;
                        params = appLinkData.getTargetUri().getHost();
                        Hawk.put(qhxsgdfync, params);
                        Log.d("FB", params);
                    } else {
                        Log.d("FB", "Error Code:");
                    }
                }
        );
    }

}