package com.ge.exchange.repository;

import com.ge.exchange.dto.PostDto;
import com.ge.exchange.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    @Query("select p from Post p where p.author.userId <> :userId")
    List<Post> getPostsOfOthers(int userId);
}
