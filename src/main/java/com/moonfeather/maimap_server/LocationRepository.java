package com.moonfeather.maimap_server;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("SELECT l FROM Location l WHERE l.name LIKE %:name% ORDER BY l.name ASC")
    List<Location> findTop5ByName(@Param("name") String name);

}


