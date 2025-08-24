package com.training.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.training.model.Chat;
import com.training.service.ChatService;
import com.training.dto.OwnerChatDto;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/chat")
public class Chatcontroller {

    @Autowired
    private ChatService chatService;

    @PostMapping("/send")
    public Chat sendMsg(@RequestBody Chat chat) {
        return chatService.send(chat);
    }

    @GetMapping("/get/{puid}")
    public List<Chat> getMsg(@PathVariable("puid") String puid) {
        String[] splitted = puid.split(",");
        int pid = Integer.parseInt(splitted[0]);
        int uid = Integer.parseInt(splitted[1]);
        return chatService.get(pid, uid);
    }

    @GetMapping("/getowner/{pid}")
    public List<Chat> getOwnerMsg(@PathVariable("pid") int oid) {
        return chatService.getowner(oid);
    }

    // NEW ENDPOINT for Owner Dashboard (user name + message)
    @GetMapping("/dashboard/{propertyId}")
    public List<OwnerChatDto> getOwnerDashboardChats(@PathVariable int propertyId) {
        return chatService.getOwnerDashboardChats(propertyId);
    }
}
