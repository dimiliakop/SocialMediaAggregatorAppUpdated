package com.example.socialmediaaggregatorupdate.API.Comparators;

import com.example.socialmediaaggregatorupdate.SM_Data.model.Post;

public class PostChronologyComparator implements PostComparator {

    @Override
    public int compare(Post o1, Post o2) {
        int result = o1.getPostedDate().compareTo(o2.getPostedDate());
        return Integer.compare(0, result);
    }

}
