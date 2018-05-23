package pl.redblue.travelsouvenire;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class NewPostsFrag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_newposts, container, false);

        RecyclerView rec = (RecyclerView) rootView.findViewById(R.id.newpostsRecycle);
        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<SinglePost> posts = new ArrayList<>();
        posts.add(new SinglePost("aaaa", "aaaa", "aaaaaa"));
        posts.add(new SinglePost("bbbb", "bbbbbb", "bbbb"));
        posts.add(new SinglePost("cccccccc", "cccc", "ccccc"));
        posts.add(new SinglePost("dddd", "ddddd", "ddddddd"));
        posts.add(new SinglePost("eeeeeeee", "eeeeee", "eeeeeee"));
        rec.setAdapter(new PostAdapter(posts,rec));
        return rootView;
    }
}
