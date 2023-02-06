package com.ge.exchange.repository;

import com.ge.exchange.dto.PostDto;
import com.ge.exchange.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    @Query("select p from Post p where p.author.userId <> :userId")
    List<Post> getPostsOfOthers(int userId);

    @Query("select p from Post p where p.author.userId = :userId")
    List<Post> getPostsOfUser(int userId);

    @Query("select p from Post p where p.title  LIKE CONCAT('',:title,'')")
    Optional<Post> getPostByTitle(String title);
}
