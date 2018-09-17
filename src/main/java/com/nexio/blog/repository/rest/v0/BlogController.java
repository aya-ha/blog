package com.nexio.blog.repository.rest.v0;

import com.nexio.blog.domain.BlogComment;
import com.nexio.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.List;


@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping(value = "/api/v0/comment", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BlogComment> getComment(@RequestParam("id") int id) {
        BlogComment blogComment = blogService.getSelectedComment(id);
        return new ResponseEntity<>(blogComment, HttpStatus.OK);
    }

    @GetMapping(value = "/api/v0/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<BlogComment>> getAllComments() {
        List<BlogComment> blogComments = blogService.getAllComments();
        return new ResponseEntity<>(blogComments, HttpStatus.OK);
    }

    @PostMapping(value = "/api/v0/comment/create", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> addNewComment(@RequestBody BlogComment blogComment) {
        blogService.addNewComment(blogComment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/v0/comment/update", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> updateComment(@RequestBody BlogComment blogComment) {
        blogService.updateComment(blogComment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/v0/comment/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deleteComment(@RequestParam("id") int id) {
        blogService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
