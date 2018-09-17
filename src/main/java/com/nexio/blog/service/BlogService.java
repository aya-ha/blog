package com.nexio.blog.service;

import com.nexio.blog.domain.BlogComment;
import com.nexio.blog.repository.entity.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public BlogComment getSelectedComment(int id){
        return blogRepository.getComment(id);
    }

    public List<BlogComment> getAllComments(){
        return blogRepository.getAllComment();
    }

    public void addNewComment(BlogComment blogComment){
        blogRepository.addNewComment(blogComment);
    }

    public void updateComment(BlogComment blogComment) {
        blogRepository.updateComment(blogComment);
    }

    public void deleteComment(int id){
        blogRepository.deleteComment(id);
    }
}
