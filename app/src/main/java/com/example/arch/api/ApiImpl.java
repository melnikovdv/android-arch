package com.example.arch.api;

import com.example.arch.api.dto.BlogItemDto;
import com.example.arch.api.dto.BlogViewsAndVotesDto;
import com.example.arch.util.Generator;

public class ApiImpl implements Api {

    private final Generator generator;

    public ApiImpl(Generator generator) {
        this.generator = generator;
    }

    @Override public BlogItemDto requestBlogItem(long id) {
        // emulating http request to server
        try {
            Thread.sleep(generator.timeoutMillis(4));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new BlogItemDto(
                id,
                generator.string(10), generator.string(150),
                generator.nextInt(100), generator.nextInt(10), generator.nextInt(10),
                System.currentTimeMillis() - generator.nextInt(100) * 1000
        );
    }

    @Override public BlogViewsAndVotesDto refreshBlogViewsAndVotes(long id) {
        // emulating http request to server
        try {
            Thread.sleep(generator.timeoutMillis(4));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new BlogViewsAndVotesDto(
                id, generator.nextInt(100), generator.nextInt(10), generator.nextInt(10)
        );
    }

}
