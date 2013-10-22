package com.forum_rallye;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View.OnClickListener;


public class MainActivity extends Activity {

    @Override
    //test de commentaire
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button btn1 = (Button) findViewById(R.id.ButtonQuit);
        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
                System.exit(0);
            }
        });
        
        ImageView img = (ImageView) findViewById(R.id.imageForum);
        img.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.forum-rallye.com"));
            	startActivity(intent);            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
