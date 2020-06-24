package com.example.instagrameclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    private EditText edtEmailID,edtUserName,edtPassWord;
    private Button btnSignUp,btnLogIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        setTitle( "SIGN UP" );

        edtEmailID=findViewById( R.id.edtEmailID );
        edtUserName=findViewById( R.id.edtUserName );
        edtPassWord=findViewById( R.id.edtPassWord );
        edtPassWord.setOnKeyListener( new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN){

                    onClick( btnSignUp );
                }
                return false;
            }
        } );
        btnLogIn=findViewById( R.id.btnLogIn );
        btnSignUp=findViewById( R.id.btnSignUp );

        btnSignUp.setOnClickListener( this );
        btnLogIn.setOnClickListener( this );

        if (ParseUser.getCurrentUser() != null){
       // ParseUser.getCurrentUser().logOut();
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignUp:
                if (edtEmailID.getText().toString().equals( "" )||
                        edtUserName.getText().toString().equals( "" )||
                        edtPassWord.getText().toString().equals( "" )){
                    FancyToast.makeText( MainActivity.this,"The Email,User Name,passWord Required ", FancyToast.LENGTH_SHORT, FancyToast.INFO, true ).show();

                    trasitionToSocialMediaActivity();
                }else {

                final ParseUser appUser =new ParseUser();
                appUser.setEmail( edtEmailID.getText().toString() );
                appUser.setUsername( edtUserName.getText().toString() );
                appUser.setPassword( edtPassWord.getText().toString() );

                final ProgressDialog progressDialog=new ProgressDialog( this );
                progressDialog.setMessage( "Signing Up"+ edtUserName.getText().toString() );
                progressDialog.show();

                appUser.signUpInBackground( new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e==null){
                           FancyToast.makeText( MainActivity.this,appUser.getEmail()+"  Is Signed Up ", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true ).show();

                            trasitionToSocialMediaActivity();

                        }else {
                            Toast.makeText( MainActivity.this, e.getMessage(), Toast.LENGTH_LONG ).show();

                        }
                        progressDialog.dismiss();
                    }
                } );}
                break;
            case R.id.btnLogIn:
                Intent intent =new Intent( MainActivity.this,LoginActivity.class );
                startActivity( intent );
                break;


        }


    }
    public void rootLayoutTapped(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService( INPUT_METHOD_SERVICE );
            inputMethodManager.hideSoftInputFromWindow( getCurrentFocus().getWindowToken(),0 );
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private void trasitionToSocialMediaActivity(){

        Intent intent =new Intent(MainActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}