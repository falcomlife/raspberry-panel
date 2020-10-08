package com.sora.hamster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.sora.mirroring.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Listener l = new Listener(
                (Button)findViewById(R.id.forward),
                (Button)findViewById(R.id.backward),
                (Button)findViewById(R.id.leftward),
                (Button)findViewById(R.id.rightward),
                (EditText) findViewById(R.id.emqxip),
                (EditText) findViewById(R.id.emqxport)
        );
        l.listenerBound();
    }
}