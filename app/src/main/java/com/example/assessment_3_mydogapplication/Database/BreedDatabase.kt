package com.example.assessment_3_mydogapplication.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.assessment_3_mydogapplication.Data.Database.DogDao
import com.example.assessment_3_mydogapplication.Database.DbModel.LocalBreed


//esta clase es la  base de datos
//importante, se requiere al menos un dao disponible en el proyecto para realizar sin errores esta clase
//porque el dao es la unica manera de comunicarcon la base de datos
@Database(entities = [LocalBreed::class], version = 3)
abstract class BreedDatabase: RoomDatabase() {
    abstract fun getDogDao(): DogDao
}