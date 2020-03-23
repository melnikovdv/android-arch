package com.example.arch.api;

import com.example.arch.api.dto.BlogItemDto;
import com.example.arch.api.dto.BlogViewsAndVotesDto;

public interface Api {

    BlogItemDto requestBlogItem(long id);

    BlogViewsAndVotesDto refreshBlogViewsAndVotes(long id);

}
