package com.uteamtec.activitydemo;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textMain;
    Button btnCall;
    Button btnExplicitcall;
    Button btnSystemCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textMain = (TextView) findViewById(R.id.textMain);
        btnCall = (Button) findViewById(R.id.btnCall);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalledActivity.class);
                intent.putExtra("testValue", 20);
                Bundle bdl = new Bundle();
                bdl.putInt("name", 5);
                intent.putExtra("test", bdl);
                startActivity(intent);
            }
        });

        btnExplicitcall = (Button) findViewById(R.id.btnExplicitCall);
        btnExplicitcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalledActivity.class);
//                startActivity(intent);
                startActivityForResult(intent, 1);
            }
        });

        btnSystemCall = (Button) findViewById(R.id.btnSystemCall);
        btnSystemCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Uri uri = Uri.parse("http://www.163.com");
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1) {
            if(data != null) {
                Bundle bdl = data.getBundleExtra("ret");
                if (bdl != null) {
                    textMain.setText(new Integer(bdl.getInt("retVal")).toString());
                }
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
