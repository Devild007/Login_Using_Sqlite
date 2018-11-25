package sqlite.offline.login;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class NewUser extends AppCompatActivity {

    EditText fullname, email, mobile, pass;
    Button btn1, btn2;
    LinearLayout l1,l2;
    Animation uptodown,downtoup;
    DataHandler db;
    String NAME, EMAIL, MOBILE, PASS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        db = new DataHandler(this);
        fullname = (com.alimuzaffar.lib.widgets.AnimatedEditText)findViewById(R.id.newname);
        email = (com.alimuzaffar.lib.widgets.AnimatedEditText) findViewById(R.id.newemail);
        mobile = (com.alimuzaffar.lib.widgets.AnimatedEditText) findViewById(R.id.newmobile);
        pass = (com.alimuzaffar.lib.widgets.AnimatedEditText) findViewById(R.id.newpassword);
        btn1 = (Button) findViewById(R.id.buttonadd);
        btn2 = (Button) findViewById(R.id.buttonlogin);
        l1 = (LinearLayout) findViewById(R.id.l1);
        l2 = (LinearLayout) findViewById(R.id.l2);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        l1.setAnimation(uptodown);
        l2.setAnimation(downtoup);

        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                NAME = fullname.getText().toString();
                EMAIL = email.getText().toString();
                MOBILE = mobile.getText().toString();
                PASS = pass.getText().toString();

                Log.i("USER", NAME);
                Log.i("EMAIL", EMAIL);
                Log.i("MOBILE", MOBILE);
                Log.i("PASS", PASS);

                if(NAME.equals("")||PASS.equals("")||EMAIL.equals("")||MOBILE.equals("")){
                    Toast.makeText(getApplicationContext(),"One of the field/fields are empty",Toast.LENGTH_SHORT).show();
                    Log.i("if - ", "PASS");
                    Log.i("if - ", "PASS");
                }
                else {
                    Log.i("else - ", "entered");
                    Log.i("else - ", "entered");

                    /*if(null==USER || null==EMAIL || null==MOBILE || null==PASS)
                    {
                        USER = new String();
                        EMAIL = new String();
                        MOBILE = new String();
                        PASS = new String();
                    }

                    USER = fullname.getText().toString();
                    EMAIL = email.getText().toString();
                    MOBILE = mobile.getText().toString();
                    PASS = pass.getText().toString();*/



                    Boolean chkemail = db.chkemail(EMAIL);

                    if(chkemail == true) {
                        Boolean insert = db.insert(NAME, EMAIL, MOBILE, PASS);

                        if (insert == true)
                        {
                            Toast.makeText(getApplicationContext(),"Congratulation!",Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Error:Email exists!",Toast.LENGTH_SHORT).show();
                    }



                 /*   db.insert(USER, EMAIL, MOBILE, PASS);
                    Log.i("if - ", "PASS");
                    Log.i("if - ", "PASS");

                    Toast.makeText(getApplicationContext(),"Congratulation!",Toast.LENGTH_SHORT).show();*/
                }
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(NewUser.this,MainActivity.class);
                finish();
                startActivity(i);

            }
        });



    }
}

