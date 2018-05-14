package com.redwood.rottenpotato.main.repositories;

import com.redwood.rottenpotato.main.models.Movie;
import com.redwood.rottenpotato.main.models.TV;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TVRepository extends JpaRepository<TV, Long> {
    @Query("SELECT t FROM TV t WHERE t.TVName LIKE %:searchTerm%")
    public List<TV> searchByName(@Param("searchTerm") String searchTerm,Pageable pageable);

    @Query("select t from TV t where t.TVKey=:TVKey")
    public TV findByTVKey(@Param("TVKey") String TVKey);

    @Modifying
    @Query("delete from TV t where t.TVKey=:TVKey")
    long removeByTVKey(@Param("TVKey") String TVKey);

    @Query("SELECT t FROM TV t order by t.TVDateDate DESC")
    public List<TV> findTop10ByOrderByTVDateDateDesc(Pageable pageable);

    @Query("SELECT t FROM TV t WHERE t.TVCast LIKE %:actorKey%")
    public List<TV> searchByActorKey(@Param("actorKey") String actorKey);


}
