package com.bigwinlab.b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.orhanobut.hawk.Hawk;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AddAct extends AppCompatActivity {

    public static final String qazwsxedcrfv = "cstR1";
    private static final String ikmju = "MBgestX5Ptax9Sbi5TuPnZ";

    String stMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            String hawkI = Hawk.get(qazwsxedcrfv);
            if (hawkI != null) {
                Log.d("TestInUIHawk", hawkI);
                rtgfhryjd();
                executorService.shutdown();
                finish();
            } else {
                Log.d("TestInUIHawk", "Received null");
            }
        }, 0, 2, TimeUnit.SECONDS);


        qwerdsa();

        SharedPreferences prefs = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        if (prefs.getBoolean("activity_exec", false)) {
            Intent intent = new Intent(this, SecondAddAc.class);
            startActivity(intent);
            finish();
        } else {
            SharedPreferences.Editor exec = prefs.edit();
            exec.putBoolean("activity_exec", true);
            exec.apply();
        }
    }



    public void rtgfhryjd() {

        Intent intent = new Intent(AddAct.this, SecondAddAc.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    public void qwerdsa() {
        AppsFlyerConversionListener conversionListener = new AppsFlyerConversionListener() {

            @Override
            public void onConversionDataSuccess(Map<String, Object> conversionData) {


                Log.d("TESTING_ZONE", "af stat is " + conversionData.get("af_status"));
                String status = (String) conversionData.get("af_status");
                if (Objects.equals(status, "Organic")) {
                    stMain = "null";
                } else {
                    stMain = (String) conversionData.get("campaign");
                }
                Hawk.put(qazwsxedcrfv, stMain);
                Log.d("NAMING TEST", "campaign attributed: " + stMain);
            }


            @Override
            public void onConversionDataFail(String errorMessage) {
                stMain = "null";
            }

            @Override
            public void onAppOpenAttribution(Map<String, String> attributionData) {
            }

            @Override
            public void onAttributionFailure(String errorMessage) {
            }

        };

        AppsFlyerLib.getInstance().init(ikmju, conversionListener, this);
        AppsFlyerLib.getInstance().start(this);
        AppsFlyerLib.getInstance().setDebugLog(true);

    }
}