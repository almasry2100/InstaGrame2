package com.example.instagrameclone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileTab extends Fragment {

    private EditText edtProfileName,edtProfileBio,edtProfileProfession,
            edtProfileHobies,edtProfileFavoriteSport;
    private Button btnProfileUpdateInfo;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileTab.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileTab newInstance(String param1, String param2) {
        ProfileTab fragment = new ProfileTab();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1, param1 );
        args.putString( ARG_PARAM2, param2 );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {
            mParam1 = getArguments().getString( ARG_PARAM1 );
            mParam2 = getArguments().getString( ARG_PARAM2 );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate( R.layout.fragment_profile_tab, container, false );

        edtProfileName=view.findViewById( R.id.edtProfileName );
        edtProfileBio=view.findViewById( R.id.edtProfileBio);
        edtProfileProfession=view.findViewById( R.id.edtProfileProfession );
        edtProfileHobies=view.findViewById( R.id.edtProfileHobies);
        edtProfileFavoriteSport=view.findViewById( R.id.edtProfileFavoriteSport );

        btnProfileUpdateInfo=view.findViewById( R.id.btnProfileUpdateInfo );
        final ParseUser parsuser=ParseUser.getCurrentUser();
        if (parsuser.get( "profileName" )==null ){
            edtProfileName.setText( "");
        }else {
            edtProfileName.setText( parsuser.get( "profileName" ).toString() );

        }
        if (parsuser.get( "profileBio" )==null ){
            edtProfileBio.setText( "");
        }else {
            edtProfileBio.setText( parsuser.get( "profileBio" ).toString() );

        }
        if (parsuser.get( "profileProfession" )==null ){
            edtProfileProfession.setText( "");
        }else {
            edtProfileProfession.setText( parsuser.get( "profileProfession" ).toString() );

        }
        if (parsuser.get( "profileHobies" )==null ){
            edtProfileHobies.setText( "");
        }else {
            edtProfileHobies.setText( parsuser.get( "profileHobies" ).toString() );

        }
        if (parsuser.get( "profileFavoriteSport" )==null ){
            edtProfileFavoriteSport.setText( "");
        }else {
            edtProfileFavoriteSport.setText( parsuser.get( "profileFavoriteSport" ).toString() );

        }






        btnProfileUpdateInfo.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          parsuser.put( "profileName",edtProfileName.getText().toString() );
          parsuser.put( "profileBio",edtProfileBio.getText().toString() );
          parsuser.put( "profileProfession",edtProfileProfession.getText().toString() );
          parsuser.put( "profileHobies",edtProfileHobies.getText().toString() );
          parsuser.put( "profileFavoriteSport",edtProfileFavoriteSport.getText().toString() );

          parsuser.saveInBackground( new SaveCallback() {
              @Override
              public void done(ParseException e) {
                  if (e==null){

                      FancyToast.makeText( getContext(),"info updated ", FancyToast.LENGTH_SHORT, FancyToast.INFO, true ).show();

                  }else {
                      FancyToast.makeText( getContext(), e.getMessage(), Toast.LENGTH_LONG,FancyToast.ERROR,true  ).show();

                  }
              }
          } );
            }
        } );


        return view;
    }
}