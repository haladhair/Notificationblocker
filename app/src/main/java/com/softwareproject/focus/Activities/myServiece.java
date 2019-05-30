package com.softwareproject.focus.Activities;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.softwareproject.focus.R;


public class myServiece extends Service {

    private MediaPlayer player;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
          player =  MediaPlayer.create(this, R.raw.billring2);
        player.setLooping(true);
        player.start();
        return  START_STICKY;
    }

    @Override

    public void onDestroy() {

        super.onDestroy();

        player.stop();
        stopService(new Intent(this, myServiece.class));

        player.release();
    }

}
