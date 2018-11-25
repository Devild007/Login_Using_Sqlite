package sqlite.offline.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;
import java.util.StringTokenizer;

/*import com.karan.churi.PermissionManager.PermissionManager;*/

public class MainActivity extends AppCompatActivity {

    EditText user, pass;
    Button btn1, btn2;
    String Signin, username, password, name;
    LinearLayout l1,l2;
    Animation uptodown,downtoup;

    DataHandler db;

    //SSharedPreferences variables
    private SharedPreferences registrationPreferences;
    private SharedPreferences.Editor registerationPrefsEditor;
    public static final String MyPREFERENCES = "MyPrefsLogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (com.alimuzaffar.lib.widgets.AnimatedEditText) findViewById(R.id.username);
        pass = (com.alimuzaffar.lib.widgets.AnimatedEditText) findViewById(R.id.password);
        btn1 = (Button) findViewById(R.id.buttonsub);
        btn2 = (Button) findViewById(R.id.ButtonNewUser);
        l1 = (LinearLayout) findViewById(R.id.l1);
        l2 = (LinearLayout) findViewById(R.id.l2);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        l1.setAnimation(uptodown);
        l2.setAnimation(downtoup);

        db = new DataHandler(this);

        registrationPreferences = getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);
        registerationPrefsEditor=registrationPreferences.edit();

        Signin = (registrationPreferences.getString("SIGNIN", ""));

        checksignin(Signin);

        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String USER = user.getText().toString();
                String PASS = pass.getText().toString();

                if(USER.equals("")||PASS.equals("")){
                    Toast.makeText(getApplicationContext(),"One of the field/fields are empty",Toast.LENGTH_SHORT).show();
                }

                Boolean checkpass = db.emailpassword(USER,PASS);
                if( checkpass == true)
                {
                    registerationPrefsEditor.putString("SIGNIN", "1");
                    registerationPrefsEditor.putString("EMAIL", USER);
                    registerationPrefsEditor.apply();

                    Intent i = new Intent(MainActivity.this,HomePage.class);
                    finish();
                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Error!",Toast.LENGTH_LONG).show();
                }


            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,NewUser.class);
                finish();
                startActivity(i);

            }
        });


    }

    public void checksignin(String Signin) {
        if (Signin.equals("1"))
        {
            Log.i("Called - ","SharedPreferences");

            Intent i = new Intent(MainActivity.this,HomePage.class);
            startActivity(i);

        }
    }

}
