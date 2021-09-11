package gr.uom.socialmediaaggregator.API.Comparators;

import gr.uom.socialmediaaggregator.SMA_Data.DataModel.Post;

public class NewestToOldestPostComparator implements PostComparator {

    @Override
    public int compare(Post o1, Post o2) {
        int result = o1.getPostedDate().compareTo(o2.getPostedDate());
        return Integer.compare(0, result);
    }

}
