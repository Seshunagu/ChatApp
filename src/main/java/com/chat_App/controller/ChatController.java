package com.chat_App.controller;

import com.chat_App.dto.ChatMessage;
import com.chat_App.dto.ChatMessageOut;
import com.chat_App.model.Message;
import com.chat_App.model.User;
import com.chat_App.repository.MessageRepository;
import com.chat_App.repository.UserRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageRepository messageRepo;
    private final UserRepository userRepo;

    public ChatController(SimpMessagingTemplate messagingTemplate,
                          MessageRepository messageRepo,
                          UserRepository userRepo) {
        this.messagingTemplate = messagingTemplate;
        this.messageRepo = messageRepo;
        this.userRepo = userRepo;
    }

    // ---------------- WebSocket: send a message ----------------
    @MessageMapping("/chat")
    public void processMessage(ChatMessage chatMessage, Principal principal) {
        User sender = userRepo.findById(chatMessage.getSenderId())
                              .orElseThrow(() -> new RuntimeException("Sender not found"));
        User receiver = userRepo.findById(chatMessage.getReceiverId())
                                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        // Save message
        Message saved = messageRepo.save(
            new Message(sender, receiver, chatMessage.getContent(), new java.util.Date())
        );

        ChatMessageOut out = new ChatMessageOut(
            sender.getId(),
            sender.getUsername(),
            receiver.getId(),
            chatMessage.getContent(),
            saved.getTimestamp()
        );

        // Send to receiver
        messagingTemplate.convertAndSendToUser(
                receiver.getEmail(), "/queue/messages", out
        );

        // Send acknowledgment to sender
        messagingTemplate.convertAndSendToUser(
                sender.getEmail(), "/queue/messages", out
        );
    }

    // ---------------- REST: fetch chat history ----------------
    @GetMapping("/history")
    public List<Message> getChatHistory(@RequestParam Long senderId,
                                        @RequestParam Long receiverId) {
        return messageRepo.findChatHistory(senderId, receiverId);
    }

    // ---------------- REST: fetch contacts ----------------
    @GetMapping("/contacts")
    public List<User> getChatContacts(Principal principal) {
        User currentUser = userRepo.findByEmail(principal.getName())
                                   .orElseThrow(() -> new RuntimeException("User not found"));
        return messageRepo.findContactsSortedByLastMessage(currentUser.getId());
    }
}
