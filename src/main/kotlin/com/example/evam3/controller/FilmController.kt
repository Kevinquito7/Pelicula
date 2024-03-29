package com.example.evam3.controller

import com.example.evam3.entity.Film
import com.example.evam3.service.FilmService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/film")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
class FilmController {
    @Autowired
    lateinit var filmService: FilmService

    @GetMapping
    fun list (): ResponseEntity<*> {
        return ResponseEntity(filmService.list(), HttpStatus.OK)
    }

    @PostMapping
    fun save (@RequestBody film: Film): ResponseEntity<*> {
        return ResponseEntity<Film>(filmService.save(film), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @RequestBody film: Film): ResponseEntity<Film> {
        val existingFilm = filmService.listById(id)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)


        existingFilm.title = film.title
        existingFilm.director = film.director

        val updatedFilm = filmService.updateName(existingFilm)

        return ResponseEntity(updatedFilm, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<*> {
        val response = filmService.delete(id)
        return ResponseEntity(filmService.listById(id), HttpStatus.OK)

    }

    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*> {
        return ResponseEntity(filmService.listById(id), HttpStatus.OK)
    }
}
