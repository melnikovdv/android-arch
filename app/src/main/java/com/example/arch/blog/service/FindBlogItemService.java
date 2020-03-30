package com.example.arch.blog.service;

import com.example.arch.api.Api;
import com.example.arch.api.dto.BlogItemDto;
import com.example.arch.api.dto.BlogViewsAndVotesDto;
import com.example.arch.blog.model.BlogItem;
import com.example.arch.blog.repo.BlogItemRepo;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton public class FindBlogItemService {

    private final BlogItemRepo blogItemRepo;
    private final Api api;

    @Inject public FindBlogItemService(BlogItemRepo blogItemRepo, Api api) {
        this.blogItemRepo = blogItemRepo;
        this.api = api;
    }

    public BlogItem findById(long id) {
        BlogItem blogItem = blogItemRepo.findById(id);
        if (blogItem == null) {
            blogItem = requestFromServer(id);
        }
        return blogItem;
    }

    private BlogItem requestFromServer(long id) {
        BlogItemDto blogItemDto = api.requestBlogItem(id);
        BlogItem blogItem = new BlogItem(
                id,
                blogItemDto.getTitle(),
                blogItemDto.getText(),
                blogItemDto.getViewCount(),
                blogItemDto.getUpVotes(),
                blogItemDto.getDownVotes(),
                blogItemDto.getCreated(),
                System.currentTimeMillis()
        );
        blogItemRepo.add(blogItem);
        return blogItem;
    }

    public BlogItem refreshViewsAndVotes(long id) {
        BlogViewsAndVotesDto blogViewsAndVotesDto = api.refreshBlogViewsAndVotes(id);
        BlogItem blogItemFromCache = blogItemRepo.findById(id);
        BlogItem blogItem = new BlogItem(
                id,
                blogItemFromCache.getTitle(),
                blogItemFromCache.getText(),
                blogViewsAndVotesDto.getViewCount(),
                blogViewsAndVotesDto.getUpVotes(),
                blogViewsAndVotesDto.getDownVotes(),
                blogItemFromCache.getCreated(),
                System.currentTimeMillis()
        );
        blogItemRepo.update(blogItem);
        return blogItem;
    }

    public List<BlogItem> findAll() {
        return blogItemRepo.findAll();
    }
}
