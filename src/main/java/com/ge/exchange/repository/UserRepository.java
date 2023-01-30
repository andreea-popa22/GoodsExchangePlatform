package com.ge.exchange.repository;

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
    // TODO use Optional
    User findByEmail(String email);

    Optional<User> findByUserId(int id);

    @Query("select r from Request r where r.requester = :user_id")
    List<Request> findRequestsForRequester(int user_id);

    @Query("select r from Request r where r.receiver = :user_id")
    List<Request> findRequestsForReceiver(int user_id);

    @Query("select m from Message m where m.sender = :user_id")
    List<Message> findMessagesForSender(int user_id);

    @Query("select m from Message m where m.receiver = :user_id")
    List<Message> findMessagesForReceiver(int user_id);

    @Query("select p from Post p where p.author = :user_id")
    List<Post> getPosts(int user_id);
}