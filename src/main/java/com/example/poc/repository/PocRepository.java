package com.example.poc.repository;

import com.example.poc.model.UserData;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** This class is used to interact with the database. */
@Repository
public interface PocRepository extends JpaRepository<UserData, Long> {

  @Query("SELECT u FROM UserData u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))")
  List<UserData> findByNameContainingIgnoreCase(@Param("name") String name);
}
