package com.training.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.training.model.Email;

@Repository
public interface EmailRepo extends JpaRepository<Email, Integer> {

    @Query(value = "SELECT * FROM emails WHERE owner_id = ?1", nativeQuery = true)
    List<Email> findByOwner_id(int owner_id);
}
