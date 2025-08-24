package com.training.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.dto.OwnerChatDto;
import com.training.model.Chat;
import com.training.repo.ChatRepo;

@Service
public class ChatService {

    @Autowired
    private ChatRepo chatRepo;

    public Chat send(Chat chat) {
        return chatRepo.save(chat);
    }

    public List<Chat> get(int pid, int uid) {
        return chatRepo.findByPidAndUid(pid, uid);
    }

    public List<Chat> getowner(int pid) {
        return chatRepo.findByPid(pid);
    }

    // Owner Dashboard: get all user chats for a property (userName, message, userId)
    public List<OwnerChatDto> getOwnerDashboardChats(int pid) {
        List<Object[]> rows = chatRepo.findOwnerDashboardChats(pid);
        List<OwnerChatDto> dtos = new ArrayList<>();
        for (Object[] row : rows) {
            String userName = (String) row[0];
            String message = (String) row[1];
            int userId = ((Number) row[2]).intValue();
            dtos.add(new OwnerChatDto(userName, message, userId));
        }
        return dtos;
    }
}
