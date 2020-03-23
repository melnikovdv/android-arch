package com.example.arch.blog.repo;

import com.example.arch.blog.model.BlogItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class BlogItemRepoImpl implements BlogItemRepo {

    private final Map<Long, BlogItem> items = new ConcurrentHashMap<>();

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
