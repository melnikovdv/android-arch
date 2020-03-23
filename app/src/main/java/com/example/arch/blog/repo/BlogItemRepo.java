package com.example.arch.blog.repo;

import com.example.arch.blog.model.BlogItem;

import java.util.List;

public interface BlogItemRepo {

    List<BlogItem> findAll();

    BlogItem findById(long id);

    BlogItem add(BlogItem blogItem);

    void update(BlogItem blogItem);

    void removeById(long id);
}
