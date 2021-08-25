package com.example.socialmediaaggregatorupdate.API.Parsers;

import java.util.List;

import com.example.socialmediaaggregatorupdate.SM_Data.model.Post;

public interface IParser<T> {

    List<Post> parse(T data);

}
