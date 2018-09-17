package com.nexio.blog.service;

import com.nexio.blog.domain.BlogComment;
import com.nexio.blog.repository.entity.BlogRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BlogCommentServiceTest {

    @Mock
    private BlogRepository blogRepository;

    @InjectMocks
    private BlogService blogService;

    @Captor
    private ArgumentCaptor<BlogComment> blogArgumentCaptor;

    @Test
    public void shouldReturnComment(){
        // Given
        BlogComment blogComment = buildBlogObject(1, "comment1");
        when(blogRepository.getComment(1)).thenReturn(blogComment);

        // When
        BlogComment result = blogService.getSelectedComment(1);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getComment()).isEqualTo(blogComment.getComment());
    }

    @Test
    public void shouldReturnListOfComment(){
        // Given
        List<BlogComment> blogComments = new ArrayList<>();
        blogComments.add(buildBlogObject(1, "comment1"));
        blogComments.add(buildBlogObject(2, "comment2"));
        when(blogRepository.getAllComment()).thenReturn(blogComments);

        // When
        List<BlogComment> result = blogService.getAllComments();

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(blogComments.size());
        assertThat(result.get(0).getComment()).isEqualTo(blogComments.get(0).getComment());
        assertThat(result.get(1).getComment()).isEqualTo(blogComments.get(1).getComment());
    }

    @Test
    public void shouldAddComment(){
        // Given
        BlogComment blogComment = buildBlogObject(1, "comment1");

        // When
        blogService.addNewComment(blogComment);

        // Then
        verify(blogRepository, times(1)).addNewComment(blogArgumentCaptor.capture());
        assertThat(blogArgumentCaptor.getValue().getComment()).isEqualTo(blogComment.getComment());
        assertThat(blogArgumentCaptor.getValue().getId()).isEqualTo(blogComment.getId());
    }

    @Test
    public void shouldUpdateComment(){
        // Given
        BlogComment blogComment = buildBlogObject(3, "comment3");

        // When
        blogService.updateComment(blogComment);

        // Then
        verify(blogRepository, times(1)).updateComment(blogArgumentCaptor.capture());
        assertThat(blogArgumentCaptor.getValue().getId()).isEqualTo(blogComment.getId());
        assertThat(blogArgumentCaptor.getValue().getComment()).isEqualTo(blogComment.getComment());
    }


    BlogComment buildBlogObject(int id, String comment) {
        BlogComment blogComment = new BlogComment();
        blogComment.setId(id);
        blogComment.setComment(comment);
        return blogComment;
    }
}
