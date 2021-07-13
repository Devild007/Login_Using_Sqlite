package com.example.loginsqlite;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DialogOK extends DialogFragment {

    @BindView(R.id.txtTitle)
    AppCompatTextView txtTitle;
    @BindView(R.id.txtMessage)
    AppCompatTextView txtMessage;
    @BindView(R.id.btnOK)
    AppCompatButton btnOK;
    @BindView(R.id.parent)
    RelativeLayout parent;
    private IDialogOKCallback callback;
    private String title = "", message = "";
    //private ThemeChecker themeChecker;

    public DialogOK() {
        // Empty constructor required for DialogFragment
    }

    public static DialogOK newInstance(String title, String message) {
        DialogOK frag = new DialogOK();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("message", message);
        frag.setArguments(args);
        return frag;
    }

    public IDialogOKCallback getCallback() {
        return callback;
    }

    public void setCallback(IDialogOKCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Dialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.dimAmount = 0.6f;
        window.setAttributes(params);
        window.setBackgroundDrawableResource(android.R.color.transparent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alert_dialog_ok, container, false);
        ButterKnife.bind(this, view);
        setDialogPosition();
        title = getArguments().getString("title");
        message = getArguments().getString("message");
        txtTitle.setText(title);
        txtMessage.setText(message);
        return view;
    }


    @OnClick(R.id.btnOK)
    public void close() {
        dismissAllowingStateLoss();
        callback.closeDialog();
    }

    private void setDialogPosition() {
        Window window = getDialog().getWindow();
        if (window != null) {
            window.setGravity(Gravity.CENTER);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    public interface IDialogOKCallback {

        void closeDialog();
    }
}
