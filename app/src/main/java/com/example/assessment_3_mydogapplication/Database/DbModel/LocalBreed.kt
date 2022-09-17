package com.example.assessment_3_mydogapplication.Database.DbModel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.assessment_3_mydogapplication.Data.Network.Domain.Breed as Breed


//Esta clase actua como el constructor de la base de datos
//yo lo asimilo como un getter and setter porque la clase
// va a leer los datos que hay en el modelo de la carpeta dominio y va a decir:
// OK lo que esta en la variable breed del modelo de dominio, ahora ponlo en la
// base de datos en la columna breed
@Entity(tableName = "local_breeds")
data class LocalBreed(
    @PrimaryKey
    val title: String,
    val breed: String,
    val subBreed: String,
    val isFaved: Boolean = false,
    val urlBreed: String? = null

    ) {
        fun toDomain(): Breed {
            return Breed(
                breed, subBreed, isFaved, urlBreed
            )
        }



}
