package com.sora.hamster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.sora.mirroring.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Listener l = new Listener(
                (Button) findViewById(R.id.forward),
                (Button) findViewById(R.id.backward),
                (Button) findViewById(R.id.leftward),
                (Button) findViewById(R.id.rightward),
                (ImageButton) findViewById(R.id.close),
                (ImageButton) findViewById(R.id.setting),
                (ImageButton) findViewById(R.id.clockwise),
                (ImageButton) findViewById(R.id.anticlockwise),
                (View) findViewById(R.id.layout_setting),
                (View) findViewById(R.id.layout_control),
                (EditText) findViewById(R.id.emqxip),
                (EditText) findViewById(R.id.emqxport)
        );
        l.listenerBound();
    }
}