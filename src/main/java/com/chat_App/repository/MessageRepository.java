package com.chat_App.repository;

import com.chat_App.model.Message;
import com.chat_App.model.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m " +
           "WHERE (m.sender.id = :u1 AND m.receiver.id = :u2) " +
           "   OR (m.sender.id = :u2 AND m.receiver.id = :u1) " +
           "ORDER BY m.timestamp ASC")
    List<Message> findChatHistory(@Param("u1") Long u1, @Param("u2") Long u2);

    @Query("SELECT u FROM User u WHERE u.id IN (" +
           "   SELECT CASE WHEN m.sender.id = :userId THEN m.receiver.id ELSE m.sender.id END " +
           "   FROM Message m " +
           "   WHERE m.sender.id = :userId OR m.receiver.id = :userId" +
           ") ORDER BY (" +
           "   SELECT MAX(m.timestamp) FROM Message m " +
           "   WHERE (m.sender.id = :userId AND m.receiver.id = u.id) OR (m.sender.id = u.id AND m.receiver.id = :userId)" +
           ") DESC")
    List<User> findContactsSortedByLastMessage(@Param("userId") Long userId);
}
