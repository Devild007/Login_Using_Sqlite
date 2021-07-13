package com.example.loginsqlite;

import android.content.Intent;
import android.os.Bundle;
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

public class CreateActivity extends AppCompatActivity {

    @BindView(R.id.edtName)
    AnimatedEditText edtName;
    @BindView(R.id.edtEmail)
    AnimatedEditText edtEmail;
    @BindView(R.id.edtMobile)
    AnimatedEditText edtMobile;
    @BindView(R.id.edtPassword)
    AnimatedEditText edtPassword;
    @BindView(R.id.layoutOne)
    LinearLayout layoutOne;
    @BindView(R.id.btnCreate)
    AppCompatButton btnCreate;
    @BindView(R.id.btnLogin)
    AppCompatButton btnLogin;
    @BindView(R.id.layoutTwo)
    RelativeLayout layoutTwo;

    Animation upToDown, downToUp;
    DataHandler db;

    private boolean closeActivity = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        ButterKnife.bind(this);


        db = new DataHandler(this);

        upToDown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downToUp = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        layoutOne.setAnimation(upToDown);
        layoutTwo.setAnimation(downToUp);


    }

    @OnClick({R.id.btnCreate})
    public void btnCreateClick() {
        closeActivity = false;

        if (edtName.getText().toString().equals("") || edtPassword.getText().toString().equals("") || edtEmail.getText().toString().equals("") || edtMobile.getText().toString().equals("")) {
            showDialog("Jinkies!", "One of the field/fields are empty");
        } else {

            /*boolean check = false;

            check = db.chkemail(edtEmail.getText().toString());
*/
            if (db.chkemail(edtEmail.getText().toString())) {

                boolean insert = db.insert(edtName.getText().toString(), edtEmail.getText().toString(), edtMobile.getText().toString(), edtPassword.getText().toString());

                if (insert) {
                    closeActivity = true;
                    showDialog("Jinkies!", "You did it!");
                }

            } else {
                showDialog("Jinkies!", "Email exists!");
            }

        }
    }

    private void showDialog(String title, String message) {
        DialogOK dialogOK = DialogOK.newInstance(title, message);
        dialogOK.setCallback(new DialogOK.IDialogOKCallback() {
            @Override
            public void closeDialog() {
                dialogOK.dismissAllowingStateLoss();
                if (closeActivity) {
                    btnLoginClick();
                }
            }
        });
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(dialogOK, null);
        ft.commitAllowingStateLoss();
    }

    @OnClick({R.id.btnLogin})
    public void btnLoginClick() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

}