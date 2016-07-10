package com.onlineteammakers.bottlechat.Intent;

import java.io.Serializable;

/**
 * Created by jason on 7/10/16.
 */
public class SinglePost implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String LOG_TAG = SinglePost.class.getSimpleName();

    private int pID;
    private int pUpVotes;
    private int pDownVotes;
    private int pCommentsCount;
    private int pLikeCount;
    private int pWidth;
    private int pHeight;

    private String pChannel;
    private String pContent;
    private String pImageURL;
    private String pSmallImageURL;
    private String pLargeImageURL;
    private String pTimeStamp;

    private boolean pIsLiked;
    private boolean pIsUpvoted;
    private boolean pIsDownVoted;
    private boolean pIsFlagged;
    private boolean pIsCommented;
    private boolean pIsImageIncluded;

    public SinglePost(int id, String channel, String content, String timestamp, int upvote, int downvote, int commentscount, int favoritecount,
                      boolean ifFlagged, boolean ifFavorite, boolean ifCommented, boolean ifImageIncluded, String imageURL, String smallImageURL,
                      String largeImageURL, int width, int height) {
        pID = id;
        pChannel = channel;
        pContent = content;
        pTimeStamp = timestamp;
        pUpVotes = upvote;
        pDownVotes = downvote;
        pIsFlagged = ifFlagged;
        pCommentsCount = commentscount;
        pLikeCount = favoritecount;
        pIsLiked = ifFavorite;
        pIsCommented = ifCommented;
        pIsImageIncluded = ifImageIncluded;
        pImageURL = imageURL;
        pSmallImageURL = smallImageURL;
        pLargeImageURL = largeImageURL;
        pWidth = width;
        pHeight = height;
    }

    @Override
    public String toString() {
        return pContent + "\n" + (pUpVotes - pDownVotes) + "\n" + pTimeStamp;
    }

    public int getVoteCount() {
        return pUpVotes - pDownVotes;
    }

    public int getpID() {
        return pID;
    }

    public void setpID(int pID) {
        this.pID = pID;
    }

    public int getpUpVotes() {
        return pUpVotes;
    }

    public void setpUpVotes(int pUpVotes) {
        this.pUpVotes = pUpVotes;
    }

    public int getpDownVotes() {
        return pDownVotes;
    }

    public void setpDownVotes(int pDownVotes) {
        this.pDownVotes = pDownVotes;
    }

    public int getpCommentsCount() {
        return pCommentsCount;
    }

    public void setpCommentsCount(int pCommentsCount) {
        this.pCommentsCount = pCommentsCount;
    }

    public int getpLikeCount() {
        return pLikeCount;
    }

    public void setpLikeCount(int pLikeCount) {
        this.pLikeCount = pLikeCount;
    }

    public int getpWidth() {
        return pWidth;
    }

    public void setpWidth(int pWidth) {
        this.pWidth = pWidth;
    }

    public int getpHeight() {
        return pHeight;
    }

    public void setpHeight(int pHeight) {
        this.pHeight = pHeight;
    }

    public String getpChannel() {
        return pChannel;
    }

    public void setpChannel(String pChannel) {
        this.pChannel = pChannel;
    }

    public String getpContent() {
        return pContent;
    }

    public void setpContent(String pContent) {
        this.pContent = pContent;
    }

    public String getpImageURL() {
        return pImageURL;
    }

    public void setpImageURL(String pImageURL) {
        this.pImageURL = pImageURL;
    }

    public String getpSmallImageURL() {
        return pSmallImageURL;
    }

    public void setpSmallImageURL(String pSmallImageURL) {
        this.pSmallImageURL = pSmallImageURL;
    }

    public String getpLargeImageURL() {
        return pLargeImageURL;
    }

    public void setpLargeImageURL(String pLargeImageURL) {
        this.pLargeImageURL = pLargeImageURL;
    }

    public String getpTimeStamp() {
        return pTimeStamp;
    }

    public void setpTimeStamp(String pTimeStamp) {
        this.pTimeStamp = pTimeStamp;
    }

    public boolean ispIsLiked() {
        return pIsLiked;
    }

    public void setpIsLiked(boolean pIsLiked) {
        this.pIsLiked = pIsLiked;
    }

    public boolean ispIsUpvoted() {
        return pIsUpvoted;
    }

    public void setpIsUpvoted(boolean pIsUpvoted) {
        this.pIsUpvoted = pIsUpvoted;
    }

    public boolean ispIsDownVoted() {
        return pIsDownVoted;
    }

    public void setpIsDownVoted(boolean pIsDownVoted) {
        this.pIsDownVoted = pIsDownVoted;
    }

    public boolean ispIsFlagged() {
        return pIsFlagged;
    }

    public void setpIsFlagged(boolean pIsFlagged) {
        this.pIsFlagged = pIsFlagged;
    }

    public boolean ispIsCommented() {
        return pIsCommented;
    }

    public void setpIsCommented(boolean pIsCommented) {
        this.pIsCommented = pIsCommented;
    }

    public boolean ispIsImageIncluded() {
        return pIsImageIncluded;
    }

    public void setpIsImageIncluded(boolean pIsImageIncluded) {
        this.pIsImageIncluded = pIsImageIncluded;
    }




}
