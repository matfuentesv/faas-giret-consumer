package com.giret.consumer.repository;

import com.giret.consumer.model.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ResourceRepository extends JpaRepository<Recurso, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Recurso r SET r.estado = :estado WHERE r.idRecurso = :id")
    void updateState(@Param("id") Long id, @Param("estado") String estado);
}
