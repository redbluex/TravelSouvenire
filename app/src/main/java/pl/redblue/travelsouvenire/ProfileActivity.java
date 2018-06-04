package pl.redblue.travelsouvenire;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProfileActivity extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        FragmentMap fragmentMap = new FragmentMap();
        FragmentPersonalProfile personalProfile = new FragmentPersonalProfile();
        FragmentPhotoAlbum photoAlbum = new FragmentPhotoAlbum();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentMapID, fragmentMap);
        transaction.replace(R.id.fragmentPersonalProfileID, personalProfile);
        transaction.replace(R.id.fragmentPhotoAlbumID, photoAlbum);

    }


}
