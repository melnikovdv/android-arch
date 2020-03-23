package com.example.arch.api.dto;

public class BlogViewsAndVotesDto {

    private final long id;
    private final int viewCount;
    private final int upVotes;
    private final int downVotes;

    public BlogViewsAndVotesDto(long id, int viewCount, int upVotes, int downVotes) {
        this.id = id;
        this.viewCount = viewCount;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
    }

    public long getId() {
        return id;
    }

    public int getViewCount() {
        return viewCount;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }
}
