package com.nexio.blog.repository.entity;

import com.nexio.blog.domain.BlogComment;

import java.util.List;

public interface BlogRepository {

    BlogComment getComment(int id);

    List<BlogComment> getAllComment();

    void addNewComment(BlogComment blogComment);

    void updateComment(BlogComment blogComment);

    void deleteComment(int id);
}
