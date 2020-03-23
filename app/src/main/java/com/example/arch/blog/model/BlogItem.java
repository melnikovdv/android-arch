package com.example.arch.blog.model;

public class BlogItem {

    private final long id;
    private final String title;
    private final String text;
    private final int viewCount;
    private final int upVotes;
    private final int downVotes;
    private final long created;
    private final long updated;

    public BlogItem(long id, String title, String text, int viewCount, int upVotes, int downVotes, long created,
            long updated) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.viewCount = viewCount;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.created = created;
        this.updated = updated;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
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

    public long getCreated() {
        return created;
    }

    public long getUpdated() {
        return updated;
    }

    public BlogItem copy(long id) {
        return new BlogItem(id, title, text, viewCount, upVotes, downVotes, created, updated);
    }
}
