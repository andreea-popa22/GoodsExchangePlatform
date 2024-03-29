package com.ge.exchange.mappers;

import com.ge.exchange.dto.PostDto;
import com.ge.exchange.dto.UserDto;
import com.ge.exchange.exception.ResourceNotFoundException;
import com.ge.exchange.model.Post;
import com.ge.exchange.model.PostCategory;
import com.ge.exchange.model.User;
import com.ge.exchange.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PostMapper {
    private UserRepository userRepository;
    private UserMapper userMapper;

    public PostMapper(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Post fromPostDto(PostDto postDto) throws ResourceNotFoundException {
        Optional<User> author = userRepository.findByUserId(postDto.getAuthorId());
        if (author.isEmpty()) {
            throw new ResourceNotFoundException("Author with id " + postDto.getAuthorId() + " does not exist.");
        }

        return new Post(postDto.getPostId(),
                postDto.getTitle(),
                PostCategory.valueOf(postDto.getCategory()),
                postDto.getDate(),
                postDto.getContent(),
                postDto.getPhotoSource(),
                author.get());
    }

    public PostDto toPostDto(Post post){
        return new PostDto(post.getPostId(),
                post.getTitle(),
                post.getCategory().toString(),
                post.getDate(),
                post.getContent(),
                post.getPhotoSource(),
                post.getAuthor().getUserId());
    }
}
