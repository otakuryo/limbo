package com.example.issam.projectuf2.View;

import android.support.design.widget.FloatingActionButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.Query;

/**
 * Created by issam on 13/02/2018.
 */

public class LikePostsFragment extends PostListFragment {

    Query setQuery(){
        return mReference.child("posts/user-likes").child(FirebaseAuth.getInstance().getUid());
    }
}
