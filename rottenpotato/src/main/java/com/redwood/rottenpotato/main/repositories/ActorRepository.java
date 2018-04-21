package com.redwood.rottenpotato.main.repositories;

import com.redwood.rottenpotato.main.models.Actor;
import com.redwood.rottenpotato.main.models.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    Actor findByActorKey(String actorKey);
}
