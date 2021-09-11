package gr.uom.socialmediaaggregator.API.Parsers;

import java.util.List;

import gr.uom.socialmediaaggregator.SMA_Data.DataModel.Post;

public interface Parser<T> {

    List<Post> parse(T data);

}
