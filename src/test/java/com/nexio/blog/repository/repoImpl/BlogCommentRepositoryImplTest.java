package com.nexio.blog.repository.repoImpl;

import com.nexio.blog.domain.BlogComment;
import com.nexio.blog.repository.entity.BlogEntity;
import com.nexio.blog.repository.entity.BlogRepositoryJpa;
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
public class BlogCommentRepositoryImplTest {

    @Mock
    private BlogRepositoryJpa blogRepositoryJpa;

    @InjectMocks
    private BlogRepositoryImpl blogRepositoryImpl;

    @Captor
    private ArgumentCaptor<BlogEntity> blogEntityArgumentCaptor;

    @Test
    public void shouldReturnBlogEntityIfFound(){
        // Given
        BlogEntity blogEntity = buildBlogEntity(1, "comment1");
        when(blogRepositoryJpa.findBlogEntitiesById(1)).thenReturn(blogEntity);

        // When
        BlogComment blogComment = blogRepositoryImpl.getComment(1);

        // Then
        assertThat(blogComment).isNotNull();
        assertThat(blogComment.getId()).isEqualTo(1);
        assertThat(blogComment.getComment()).isEqualTo("comment1");
    }

    @Test
    public void shouldReturnNullIfNotFound(){
        // Given
        BlogEntity blogEntity = null;
        when(blogRepositoryJpa.findBlogEntitiesById(1)).thenReturn(blogEntity);

        // When
        BlogComment blogComment = blogRepositoryImpl.getComment(1);

        // Then
        assertThat(blogComment).isNull();
    }

    @Test
    public void shouldReturnListOfComment(){
        // Given
        List<BlogEntity> blogEntities = new ArrayList<>();
        blogEntities.add(buildBlogEntity(1, "comment1"));
        blogEntities.add(buildBlogEntity(2, "comment2"));
        when(blogRepositoryJpa.findAll()).thenReturn(blogEntities);

        // When
        List<BlogComment> blogComments = blogRepositoryImpl.getAllComment();

        // Then
        assertThat(blogComments).isNotNull();
        assertThat(blogComments).hasSize(2);
        assertThat(blogComments.get(0).getId()).isEqualTo(1);
        assertThat(blogComments.get(0).getComment()).isEqualTo("comment1");
        assertThat(blogComments.get(1).getId()).isEqualTo(2);
        assertThat(blogComments.get(1).getComment()).isEqualTo("comment2");
    }

    @Test
    public void shouldAddNewComment(){
        // Given
        BlogComment blogComment = buildBlogObject(1234, "comment1234");

        // When
        blogRepositoryImpl.addNewComment(blogComment);

        // Then
        verify(blogRepositoryJpa, times(1)).save(blogEntityArgumentCaptor.capture());
        BlogEntity blogEntity = blogEntityArgumentCaptor.getValue();
        assertThat(blogEntity.getId()).isEqualTo(blogComment.getId());
        assertThat(blogEntity.getComments()).isEqualTo(blogComment.getComment());
    }

    @Test
    public void shouldUpdateComment(){
        // Given
        BlogEntity blogEntity = buildBlogEntity(4567, "comment4567");
        when(blogRepositoryJpa.findBlogEntitiesById(4567)).thenReturn(blogEntity);
        BlogComment blogComment = buildBlogObject(4567, "comment4567Updated");

        // When
        blogRepositoryImpl.updateComment(blogComment);

        // Then
        verify(blogRepositoryJpa, times(1)).save(blogEntityArgumentCaptor.capture());
        BlogEntity result = blogEntityArgumentCaptor.getValue();
        assertThat(result.getId()).isEqualTo(blogComment.getId());
        assertThat(result.getComments()).isEqualTo(blogComment.getComment());
    }

    @Test
    public void shouldDeleteComment(){
        // Given
        BlogComment blogComment = buildBlogObject(6789, "comment6789");
        BlogEntity blogEntity = buildBlogEntity(6789, "comment6789");
        when(blogRepositoryJpa.findBlogEntitiesById(6789)).thenReturn(blogEntity);


        // When
        blogRepositoryImpl.deleteComment(blogComment.getId());

        // Then
        verify(blogRepositoryJpa, times(1)).delete(blogEntityArgumentCaptor.capture());
        BlogEntity result = blogEntityArgumentCaptor.getValue();
        assertThat(result.getId()).isEqualTo(blogComment.getId());
        assertThat(result.getComments()).isEqualTo(blogComment.getComment());
    }

    BlogEntity buildBlogEntity(int id, String comment) {
        BlogEntity blogEntity = new BlogEntity();
        blogEntity.setId(id);
        blogEntity.setComments(comment);
        return blogEntity;
    }

    BlogComment buildBlogObject(int id, String comment){
        BlogComment blogComment = new BlogComment();
        blogComment.setId(id);
        blogComment.setComment(comment);
        return blogComment;
    }
}
