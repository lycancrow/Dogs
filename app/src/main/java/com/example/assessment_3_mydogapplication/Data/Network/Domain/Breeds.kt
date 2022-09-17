package com.example.assessment_3_mydogapplication.Data.Network.Domain

import com.example.assessment_3_mydogapplication.Database.DbModel.LocalBreed
import java.io.Serializable
import java.util.*

data class Breed(
    val breed: String,
    val subBreed: String = "",
    var isFaved: Boolean,
     val urlBreed: String?



) : Comparable<Breed>, Serializable {
    val title: String
        get() = if (subBreed.isNotEmpty())
            "${subBreed.replaceFirstChar { it.titlecase(Locale.CANADA) }} ${breed.replaceFirstChar { it.titlecase(Locale.CANADA) }}"
        else
            breed.replaceFirstChar { it.titlecase(Locale.CANADA) }

    override fun compareTo(other: Breed): Int {
        val compareBreed = breed.compareTo(other.breed)
        return if (compareBreed != 0) compareBreed else title.compareTo(other.title)
    }


    //esta funcion es la que hace referencia el punto 8 del archivo PASOS PAPRA CREAR LA BASE DE DATOS
    //aqui, lo que se hace es crear una funcion que es de tipo localBreed (el modelo de la base de datos) y
    //de retornara las variables con la informacion hacia la base de datos.
    //en el video del profesor, la funcion toLocal esta en el modelo web, asi que todo lo que haga referencia a to local debera ser
    //direccionado a breeds en lugar del modelo web(DogBreeds)
    fun toLocal(): LocalBreed {
        return  LocalBreed(
            title, breed,subBreed ,isFaved, urlBreed
        )
    }


}