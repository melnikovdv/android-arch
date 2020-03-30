package com.example.arch.blog.repo;

import com.example.arch.blog.model.BlogItem;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton public class BlogItemRepoImpl implements BlogItemRepo {

    private final Map<Long, BlogItem> items = new ConcurrentHashMap<>();

    @Inject public BlogItemRepoImpl() {
    }

    @Override public List<BlogItem> findAll() {
        return new ArrayList<>(items.values());
    }

    @Override public BlogItem findById(long id) {
        return items.get(id);
    }

    @Override public BlogItem add(BlogItem blogItem) {
        items.put(blogItem.getId(), blogItem);
        return blogItem;
    }

    @Override public void update(BlogItem blogItem) {
        items.put(blogItem.getId(), blogItem);
    }

    @Override public void removeById(long id) {
        items.remove(id);
    }
}
