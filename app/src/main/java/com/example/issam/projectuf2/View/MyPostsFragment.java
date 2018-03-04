package com.example.issam.projectuf2.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.Query;

/**
 * Created by issam on 13/02/2018.
 */

public class MyPostsFragment extends PostListFragment {
    Query setQuery(){
        return mReference.child("posts/user-posts").child(FirebaseAuth.getInstance().getUid());
    }
}
