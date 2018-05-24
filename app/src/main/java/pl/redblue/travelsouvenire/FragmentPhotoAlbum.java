package pl.redblue.travelsouvenire;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class FragmentPhotoAlbum extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_photo_album, container, false);
        GridView gridView = (GridView)view.findViewById(R.id.gridViewPhoto);
        //gridView.setAdapter(new ImageAdapter(view));


        return view;
    }
}
