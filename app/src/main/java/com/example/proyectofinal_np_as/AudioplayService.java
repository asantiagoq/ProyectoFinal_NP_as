package com.example.proyectofinal_np_as;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.io.IOException;

public class AudioplayService extends Service {

    private MediaPlayer mediaPlayer;
    public static final String FILENAME = "FILENAME";
    public static final String COMMAND = "COMMAND";
    public static final String PLAY = "PLAY";
    public static final String STOP = "STOP";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String filename = intent.getStringExtra(FILENAME);
        String command = intent.getStringExtra(COMMAND);

        if (command != null) {
            if (command.equals(PLAY)) {
                audioPlay(filename);
            } else if (command.equals(STOP)) {
                audioStop();
            }
        }

        return START_STICKY;
    }

    private void audioPlay(String filename) {
        try {
            if (filename != null) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
                AssetFileDescriptor assetFileDescriptor = getAssets().openFd(filename);
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(
                        assetFileDescriptor.getFileDescriptor(),
                        assetFileDescriptor.getStartOffset(),
                        assetFileDescriptor.getLength()
                );

                assetFileDescriptor.close();
                mediaPlayer.prepare();
                mediaPlayer.setVolume(1f, 1f);
                mediaPlayer.setLooping(false);
                mediaPlayer.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void audioStop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
