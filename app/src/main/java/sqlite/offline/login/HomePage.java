package sqlite.offline.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {

    //SSharedPreferences variables
    private SharedPreferences registrationPreferences;
    private SharedPreferences.Editor registerationPrefsEditor;
    public static final String MyPREFERENCES = "MyPrefsLogin";

    String user;
    TextView name, email, mobile, pass;
    String NAME, EMAIL, MOBILE, PASS;
    ImageButton log;

    LinearLayout l1,l2;
    Animation uptodown,downtoup;
    DataHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        l1 = (LinearLayout) findViewById(R.id.l1);
        l2 = (LinearLayout) findViewById(R.id.l2);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        l1.setAnimation(uptodown);
        l2.setAnimation(downtoup);

        name = (TextView) findViewById(R.id.NAME);
        email = (TextView) findViewById(R.id.EMAIL);
        mobile = (TextView) findViewById(R.id.MOBILE);
        pass = (TextView) findViewById(R.id.PASSWORD);


        registrationPreferences = getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);
        registerationPrefsEditor=registrationPreferences.edit();
        db = new DataHandler(this);

        user = (registrationPreferences.getString("EMAIL", ""));

        Cursor cursor = db.getdata(user);

        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(),"Error!",Toast.LENGTH_LONG).show();
        }
        else {
            while (cursor.moveToNext()){
                NAME = cursor.getString(0);
                EMAIL = cursor.getString(1);
                MOBILE = cursor.getString(2);
                PASS = cursor.getString(3);

                name.setText(NAME);
                email.setText(EMAIL);
                mobile.setText(MOBILE);
                pass.setText(PASS);
            }
        }

        log = (ImageButton) findViewById(R.id.imageButton);

        log.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                registerationPrefsEditor.putString("SIGNIN", "0");
                registerationPrefsEditor.apply();

                Intent i = new Intent(HomePage.this,MainActivity.class);
                finish();
                startActivity(i);
            }

        });
    }


}
