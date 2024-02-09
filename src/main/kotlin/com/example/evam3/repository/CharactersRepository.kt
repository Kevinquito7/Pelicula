package com.example.evam3.repository

import com.example.evam3.entity.Characters
import com.example.evam3.entity.Scene
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CharactersRepository:JpaRepository<Characters, Long> {
    fun findById(id: Long?): Characters?
    /*fun findBySceneId (sceneId: Long?): List<Character>*/
}