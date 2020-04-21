package com.studymonday.demoapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.IUnityBannerListener;
import com.unity3d.services.banners.UnityBanners;

public class MainActivity extends AppCompatActivity  {

    private String unityGameID = "3564940";
    private Boolean testMode = false;
    private String placementIdSkippedVideo="video";
    private String placementIdRewardedVideo="rewardedVideo";
    private String placementIdBanner="myBanner";

    Button showSkippedVideoAd,showRewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final UnityAdsListener myAdsListener = new UnityAdsListener ();


        UnityAds.initialize (this, unityGameID, myAdsListener, testMode);

        showSkippedVideoAd=(Button)findViewById(R.id.showSkippedVideoBtn);
        showSkippedVideoAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadshowSkippedVideoAd();
            }
        });
        showRewardedVideoAd=(Button)findViewById(R.id.showRewaredVideoBtn);
        showRewardedVideoAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               LoadshowRewardedVideoAd();
            }
        });


        final UnityBannerAdsListner unityBannerAdsListner=new UnityBannerAdsListner();
        UnityBanners.setBannerListener(unityBannerAdsListner);

        UnityBanners.loadBanner(this,placementIdBanner);

    }

    public void LoadshowSkippedVideoAd()
    {
        UnityAds.load(placementIdSkippedVideo);

        if(UnityAds.isReady(placementIdSkippedVideo))
        {
            UnityAds.show(this,placementIdSkippedVideo);
        }
    }

    public void LoadshowRewardedVideoAd()
    {
        UnityAds.load(placementIdRewardedVideo);

        if(UnityAds.isReady(placementIdRewardedVideo))
        {
            UnityAds.show(this,placementIdRewardedVideo);
        }
    }




    private class UnityBannerAdsListner implements IUnityBannerListener{

        @Override
        public void onUnityBannerLoaded(String s, View view) {
            ((ViewGroup)findViewById(R.id.unity_banner_layout)).removeView(view);
            ((ViewGroup)findViewById(R.id.unity_banner_layout)).addView(view);


            Toast.makeText(MainActivity.this, "on Unity Banner Ad Loaded", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onUnityBannerUnloaded(String s) {
            Toast.makeText(MainActivity.this, "on Unity Banner Ad UnLoaded", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onUnityBannerShow(String s) {
            Toast.makeText(MainActivity.this, "on Unity Banner Ad Show", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onUnityBannerClick(String s) {
            Toast.makeText(MainActivity.this, "on Unity Banner Ad Click", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onUnityBannerHide(String s) {
            Toast.makeText(MainActivity.this, "on Unity Banner Ad Hide", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onUnityBannerError(String s) {
            Toast.makeText(MainActivity.this, "on Unity Banner Ad Error", Toast.LENGTH_SHORT).show();

        }
    }



    private class UnityAdsListener implements IUnityAdsListener {

        @Override
        public void onUnityAdsReady (String placementId) {
            Toast.makeText(MainActivity.this, "on Unity Ads Ready", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onUnityAdsStart (String placementId) {
            Toast.makeText(MainActivity.this, "on Unity Ads Start", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onUnityAdsFinish (String placementId, UnityAds.FinishState finishState) {

               if (finishState == UnityAds.FinishState.COMPLETED) {
                   Toast.makeText(MainActivity.this, "Unity Rewarded Video Ad Completed ", Toast.LENGTH_SHORT).show();
               } else if (finishState == UnityAds.FinishState.SKIPPED) {
                   Toast.makeText(MainActivity.this, "Unity Rewarded Video Ad Skipped ", Toast.LENGTH_SHORT).show();

               } else if (finishState == UnityAds.FinishState.ERROR) {
                   Toast.makeText(MainActivity.this, "Unity Rewarded Video Ad Error ", Toast.LENGTH_SHORT).show();

               }


            Toast.makeText(MainActivity.this, "on Unity Ads Finish", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onUnityAdsError (UnityAds.UnityAdsError error, String message) {
            Toast.makeText(MainActivity.this, "on Unity Ads Error", Toast.LENGTH_SHORT).show();

        }
    }



}
