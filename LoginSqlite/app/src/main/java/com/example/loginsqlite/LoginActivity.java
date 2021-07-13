package com.example.loginsqlite;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentTransaction;

import com.alimuzaffar.lib.widgets.AnimatedEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edtUsername)
    AnimatedEditText edtUsername;
    @BindView(R.id.edtPassword)
    AnimatedEditText edtPassword;
    @BindView(R.id.layoutOne)
    LinearLayout layoutOne;
    @BindView(R.id.btnSubmit)
    AppCompatButton btnSubmit;
    @BindView(R.id.btnCreate)
    AppCompatButton btnCreate;
    @BindView(R.id.layoutTwo)
    RelativeLayout layoutTwo;


    DataHandler db;
    String signin, username, password, name;

    //SSharedPreferences variables
    private SharedPreferences registrationPreferences;
    private SharedPreferences.Editor registerationPrefsEditor;
    public static final String MyPREFERENCES = "MyPrefsLogin";
    Animation upToDown, downToUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        upToDown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downToUp = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        layoutOne.setAnimation(upToDown);
        layoutTwo.setAnimation(downToUp);


        db = new DataHandler(this);

        registrationPreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        registerationPrefsEditor = registrationPreferences.edit();

        signin = (registrationPreferences.getString("SIGNIN", ""));

        checkSignin(signin);

    }

    @OnClick({R.id.btnSubmit})
    public void btnSubmitClick() {

        if (edtUsername.getText().toString().equals("") || edtPassword.getText().toString().equals("")) {

            showDialog("Jinkies!", "One of the field/fields are empty");
        }

        //boolean check = db.emailpassword(edtUsername.getText().toString(), edtPassword.getText().toString());

        if (db.emailpassword(edtUsername.getText().toString(), edtPassword.getText().toString())) {
            registerationPrefsEditor.putString("SIGNIN", "1");
            registerationPrefsEditor.putString("EMAIL", edtUsername.getText().toString());
            registerationPrefsEditor.apply();

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        } else {

            showDialog("Jinkies!", "Something went wrong? Check email / password.");
        }

    }

    @OnClick({R.id.btnCreate})
    public void btnCreateClick() {
        Intent i = new Intent(this, CreateActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }


    private void checkSignin(String signin) {
        if (signin.equals("1")) {
            Log.i("Called - ", "SharedPreferences");

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();

        }
    }

    private void showDialog(String title, String message) {
        DialogOK dialogOK = DialogOK.newInstance(title, message);
        dialogOK.setCallback(new DialogOK.IDialogOKCallback() {
            @Override
            public void closeDialog() {
                dialogOK.dismissAllowingStateLoss();
            }
        });
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(dialogOK, null);
        ft.commitAllowingStateLoss();
    }


}