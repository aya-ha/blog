package com.nexio.blog.repository.entity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepositoryJpa extends JpaRepository<BlogEntity, Long> {

    BlogEntity findBlogEntitiesById(int id);

    List<BlogEntity> findAll();

    BlogEntity save(BlogEntity blogEntity);

    void delete(BlogEntity blogEntity);
}
