package com.example.loginsqlite;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txtName)
    AppCompatTextView txtName;
    @BindView(R.id.txtEmail)
    AppCompatTextView txtEmail;
    @BindView(R.id.txtMobile)
    AppCompatTextView txtMobile;
    @BindView(R.id.txtPassword)
    AppCompatTextView txtPassword;
    @BindView(R.id.layoutOne)
    LinearLayout layoutOne;
    @BindView(R.id.imageButton)
    AppCompatImageButton imageButton;
    @BindView(R.id.layoutTwo)
    RelativeLayout layoutTwo;

    Animation upToDown, downToUp;

    private String user = "";
    DataHandler db;

    //SSharedPreferences variables
    private SharedPreferences registrationPreferences;
    private SharedPreferences.Editor registerationPrefsEditor;
    public static final String MyPREFERENCES = "MyPrefsLogin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        upToDown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downToUp = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        layoutOne.setAnimation(upToDown);
        layoutTwo.setAnimation(downToUp);


        db = new DataHandler(this);

        registrationPreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        registerationPrefsEditor = registrationPreferences.edit();

        user = (registrationPreferences.getString("EMAIL", ""));

        Cursor cursor = db.getdata(user);

        if (cursor.getCount() == 0) {
            showDialog("Jinkies!", "Something went wrong!");
        } else {
            while (cursor.moveToNext()) {

                txtName.setText(cursor.getString(0));
                txtEmail.setText(cursor.getString(1));
                txtMobile.setText(cursor.getString(2));
                txtPassword.setText(cursor.getString(3));

            }
        }

    }

    @OnClick(R.id.imageButton)
    public void imageButtonClick() {
        registerationPrefsEditor.putString("SIGNIN", "0");
        registerationPrefsEditor.apply();

        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
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