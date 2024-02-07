package com.example.evam3.service
import com.example.evam3.entity.Characters
import com.example.evam3.entity.Scene
import com.example.evam3.repository.CharactersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class CharactersService {
    @Autowired
    lateinit var charactersRepository: CharactersRepository

    fun list ():List<Characters>{
        return charactersRepository.findAll()
    }

    fun save (characters: Characters): Characters{
        try{
            characters.description?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Film no debe ser vacio")
            return charactersRepository.save(characters)
        }
        catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,ex.message)
        }
    }
    fun update(characters: Characters): Characters {
        try {
            charactersRepository.findById(characters.id)
                ?: throw Exception("ID no existe")

            return charactersRepository.save(characters)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun updateName(characters: Characters): Characters {
        try{
            val response = charactersRepository.findById(characters.id)
                ?: throw Exception("ID no existe")
            response.apply {
                description=characters.description //un atributo del modelo
            }
            return charactersRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun listById (id:Long?): Characters?{
        return charactersRepository.findById(id)
    }
    fun delete (id: Long?):Boolean?{
        try{
            val response = charactersRepository.findById(id)
                ?: throw Exception("ID no existe")
            charactersRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}