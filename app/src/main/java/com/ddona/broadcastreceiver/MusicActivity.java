package com.ddona.broadcastreceiver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

public class MusicActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION);
        } else {
            queryMusic();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                queryMusic();
            }
        }
    }

    private void queryMusic() {
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int titleIndex = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                int idIndex = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
                int albumIndex = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
                int artistIndex = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID);
                int pathIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);

                String title = cursor.getString(titleIndex);
                long id = cursor.getLong(idIndex);
                long albumId = cursor.getLong(albumIndex);
                long artistId = cursor.getLong(artistIndex);
                String path = cursor.getString(pathIndex);
                Log.d("doanpt", "title:" + title +
                        "id:" + id +
                        "albumId:" + albumId +
                        "artistId:" + artistId +
                        "path:" + path);
            }
        } else {
            Log.d("doanpt", "cursor null");
        }
    }
}