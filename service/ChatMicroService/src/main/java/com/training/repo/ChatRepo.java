package com.training.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.training.model.Chat;

@Repository
public interface ChatRepo extends JpaRepository<Chat, Integer> {

    // For owner dashboard: all user chats for a property (user name, message, uid)
    @Query(value = "SELECT username, message, uid FROM chat WHERE pid = ?1", nativeQuery = true)
    List<Object[]> findOwnerDashboardChats(int pid);

    // Existing methods...
    @Query(value = "SELECT * FROM chat WHERE pid=?1 AND uid=?2", nativeQuery = true)
    List<Chat> findByPidAndUid(int pid, int uid);

    @Query(value = "SELECT * FROM chat WHERE pid=?1", nativeQuery = true)
    List<Chat> findByPid(int pid);
}
