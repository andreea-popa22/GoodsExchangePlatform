package com.ge.exchange.repository;

import com.ge.exchange.dto.UserDto;
import com.ge.exchange.model.Message;
import com.ge.exchange.model.Post;
import com.ge.exchange.model.Request;
import com.ge.exchange.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select u from User u where u.email = :email")
    Optional<User> findUserByEmail(String email);

    Optional<User> findByUserId(int id);

    @Query("select r from Request r where r.receiver.userId = :user_id")
    List<Request> findRequestsForRequester(int user_id);

    @Query("select r from Request r where r.receiver.userId = :user_id")
    List<Request> findRequestsForReceiver(int user_id);

    @Query("select m from Message m where m.sender.userId = :user_id")
    List<Message> findMessagesForSender(int user_id);

    @Query("select m from Message m where m.receiver.userId = :user_id")
    List<Message> findMessagesForReceiver(int user_id);

    @Query("select p from Post p where p.author.userId = :user_id")
    List<Post> getPosts(int user_id);
}