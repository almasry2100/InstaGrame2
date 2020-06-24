package com.example.instagrameclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtNameLoginActivity,edtPassWordLoginActivity;
    Button  btnLoginActivity,btnSignUpLoginActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );



        edtNameLoginActivity =findViewById( R.id.edtNameLoginActivity );
        edtPassWordLoginActivity=findViewById( R.id.edtPassWordLoginActivity );

        edtPassWordLoginActivity.setOnKeyListener( new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN){
                    onClick( btnLoginActivity );
                }
                return false;
            }
        } );
        btnLoginActivity=findViewById( R.id.btnLoginActivity );
        btnSignUpLoginActivity=findViewById( R.id.btnSignUpLoginActivity );
        btnLoginActivity.setOnClickListener( this );
        btnSignUpLoginActivity.setOnClickListener( this );

        if (ParseUser.getCurrentUser() != null){
            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btnLoginActivity:
                if (edtNameLoginActivity.getText().toString().equals( "" )||edtPassWordLoginActivity.getText().toString().equals( "" )){

                    FancyToast.makeText( LoginActivity.this," the Email , PassWord Missing", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true ).show();

                }else {

                ParseUser.logInInBackground( edtNameLoginActivity.getText().toString(), edtPassWordLoginActivity.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user !=null && e ==null){

                            FancyToast.makeText( LoginActivity.this,user.getUsername()+"  login ", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true ).show();

                            trasitionToSocialMediaActivity();

                        }else {
                            Toast.makeText( LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG ).show();

                        }
                    }
                } );}

                break;


            case R.id.btnSignUpLoginActivity:
        }

    }
    private void trasitionToSocialMediaActivity(){

        Intent intent =new Intent(LoginActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}