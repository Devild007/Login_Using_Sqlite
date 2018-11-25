package sqlite.offline.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    LinearLayout l1,l2;
    Animation uptodown,downtoup,textanim;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        l1 = (LinearLayout) findViewById(R.id.l1);
        l2 = (LinearLayout) findViewById(R.id.l2);
        text = (TextView) findViewById(R.id.textanimate);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        textanim = AnimationUtils.loadAnimation(this,R.anim.textanim);
        l1.setAnimation(uptodown);
        l2.setAnimation(downtoup);
        text.setAnimation(textanim);

        Thread t1 = new Thread()
        {
            @Override
            public void run() {
                try
                {
                    sleep(10000);

                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    finish();
                    startActivity(i);
                }
                catch(Exception e){}
            }
        };
        t1.start();
    }
}
