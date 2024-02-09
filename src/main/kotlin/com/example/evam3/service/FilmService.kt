package com.example.evam3.service

import com.example.evam3.entity.Film
import com.example.evam3.entity.Scene
import com.example.evam3.repository.FilmRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class FilmService {
    @Autowired
    lateinit var filmRepository: FilmRepository

    fun list ():List<Film>{
        return filmRepository.findAll()
    }

    fun save (film:Film): Film{
        try{
            film.title?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Film no debe ser vacio")
            return filmRepository.save(film)
        }
        catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,ex.message)
        }
    }

    /*
    fun save(character: Character): Character{
        try{
            val scene = sceneRepository.findById(character.sceneId)
            val listCharacter = characterRepository.findBySceneId(character.sceneId)
            var sum = 0.0

            listCharacter.map {
                sum += sum * it.cost
            }

            if (scene?.budget!! <= sum + character.cost) {
                throw IllegalArgumentException("El costo total de los personajes supera el presupuesto de la escena")
            }

            return characterRepository.save(character)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    */

    fun updateName(film: Film): Film {
        try{
            val response = filmRepository.findById(film.id)
                ?: throw Exception("ID no existe")
            response.apply {
                title=film.title
            director=film.director
          //un atributo del modelo
            }
            return filmRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun listById (id:Long?): Film?{
        return filmRepository.findById(id)
    }
    fun delete (id: Long?):Boolean?{
        try{
            val response = filmRepository.findById(id)
                ?: throw Exception("ID no existe")
            filmRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}