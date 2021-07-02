package com.teguh.sejarahislam.services;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.teguh.sejarahislam.R;
import com.teguh.sejarahislam.common.Music;

public class BackgroundMusicGamesService extends Service {
    public static MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.piano_bg_game);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(100, 100);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!Music.OFF) mediaPlayer.start();
        return startId;
    }

    @Override
    public void onStart(Intent intent, int startId) {

    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    public void onLowMemory() {

    }

}


