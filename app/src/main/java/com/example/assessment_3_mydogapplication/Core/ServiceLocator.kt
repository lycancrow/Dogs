package com.example.assessment_3_mydogapplication.Core

import android.content.Context
import androidx.room.Room

import com.example.assessment_3_mydogapplication.Data.Network.ApiService
import com.example.assessment_3_mydogapplication.Database.BreedDatabase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ServiceLocator (private val applicationContext: Context) {

    //Companion object hace referencia a un elemento comun en toda la aplicacion (instancias de clase)
    companion object {
        private const val BASE_URL = "https://dog.ceo/api/"

        private const val  DB_NAME = "local_breeds" // ACA SE DICE que la base de datos es comun para toda la applicacion
    }

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)


    //Esta parte del codigo inicializa la base de datos, basicamente se dice que se va a crear una varible
    // de nombre dogDatabase que es igual al constrictor de la base de datos, haciendo que se pueda aplicar
    // esta variable en cualquier clase del sistema en cuestion
    val breedDatabase = Room.databaseBuilder(
        applicationContext,
        BreedDatabase::class.java,
        DB_NAME
    ).fallbackToDestructiveMigration().build()

}