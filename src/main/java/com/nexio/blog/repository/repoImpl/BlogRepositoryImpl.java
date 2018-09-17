package com.nexio.blog.repository.repoImpl;

import com.nexio.blog.domain.BlogComment;
import com.nexio.blog.repository.entity.BlogEntity;
import com.nexio.blog.repository.entity.BlogRepository;
import com.nexio.blog.repository.entity.BlogRepositoryJpa;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogRepositoryImpl implements BlogRepository {

    private BlogRepositoryJpa blogRepositoryJpa;


    @Inject
    public BlogRepositoryImpl(BlogRepositoryJpa blogRepositoryJpa) {
        this.blogRepositoryJpa = blogRepositoryJpa;
    }

    @Override
    public BlogComment getComment(int id) {
        BlogEntity blogEntity = blogRepositoryJpa.findBlogEntitiesById(id);
        return convertEntityToBlogObject(blogEntity);
    }

    @Override
    public List<BlogComment> getAllComment() {
        List<BlogComment> blogComments = new ArrayList<>();
        List<BlogEntity> blogEntities = blogRepositoryJpa.findAll();
        if(!blogEntities.isEmpty()){
            for(BlogEntity blogEntity: blogEntities){
                blogComments.add(convertEntityToBlogObject(blogEntity));
            }
        }
        return blogComments;
    }

    @Override
    public void addNewComment(BlogComment blogComment) {
        BlogEntity blogEntity = new BlogEntity();
        blogEntity.setId(blogComment.getId());
        blogEntity.setComments(blogComment.getComment());
        blogRepositoryJpa.save(blogEntity);
    }

    @Override
    public void updateComment(BlogComment blogComment) {
        BlogEntity blogEntity = blogRepositoryJpa.findBlogEntitiesById(blogComment.getId());
        blogEntity.setComments(blogComment.getComment());
        blogRepositoryJpa.save(blogEntity);
    }

    @Override
    public void deleteComment(int id) {
        BlogEntity  blogEntity = blogRepositoryJpa.findBlogEntitiesById(id);
        blogRepositoryJpa.delete(blogEntity);
    }

    private BlogComment convertEntityToBlogObject(BlogEntity blogEntity) {
        if (blogEntity != null) {
            BlogComment blogComment = new BlogComment();
            blogComment.setId(blogEntity.getId());
            blogComment.setComment(blogEntity.getComments());
            return blogComment;
        }
        return null;
    }
}
