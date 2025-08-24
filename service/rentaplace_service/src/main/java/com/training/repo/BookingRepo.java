package com.training.repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.training.model.Booking;
import com.training.model.Property;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {

    List<Booking> findByStatus(Boolean status);
    
    List<Booking> findByUserId(int userId); // THIS IS THE BEST PRACTICE!



    // Correct: returns List<Booking>
    List<Booking> findByProperty(Property property);

    @Query(value = "SELECT * FROM booking WHERE checkout <= ?1 OR status = false", nativeQuery = true)
    List<Booking> findByCheckins(Date checkin);

    @Query(value = "SELECT * FROM booking WHERE (checkin >= ?1 AND checkin >= ?2) OR (checkout <= ?1 AND checkout <= ?2) OR user_id = 0", nativeQuery = true)
    List<Booking> findByCheckinCheckout(Date checkinDate, Date checkoutDate);
}
