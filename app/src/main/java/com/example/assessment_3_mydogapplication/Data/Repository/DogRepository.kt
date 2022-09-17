package com.example.assessment_3_mydogapplication.Data.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map

import com.example.assessment_3_mydogapplication.Data.Network.ApiService
import com.example.assessment_3_mydogapplication.Database.BreedDatabase
import com.example.assessment_3_mydogapplication.Data.Network.Domain.Breed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException


//ahora se debe pasar la base de datos al repositorio, por eso se declara  una varible de tipo BreedDatabase

class DogRepository(private val apiService: ApiService,
                    private val breedDatabase: BreedDatabase
                    //Importante, al hacer este cambio hay que modificar el llamado en todas las actividades que estan usando el
                    // repositorio (en este caso el MainActivity y el ImageActivity) porque
                    // el Main en este proyecto solo esta esperando un valor.

                    //MIRAR ARCHIVO PASOS PARA CREAR LA BASE DE DATOS despues de haber configurado las listas del comentario anterior

                    ) {

   // private val _breedList = MutableLiveData<List<Breed>?>()


    val breedList: LiveData<List<Breed>?> get() = breedDatabase.getDogDao().getBreedInfos().map{
        it.map{it.toDomain()}
   }

    val favedBreedInfos: LiveData<List<Breed>?> get() = breedDatabase.getDogDao().getFavedBreeds().map{
        it.map{it.toDomain()}
    }

    private val _randomImage = MutableLiveData<String>()
    val randomImage: LiveData<String> get() = _randomImage






    suspend fun loadDogList() {
        val response = try {
            apiService.getBreed()
        } catch (e: HttpException) {
            null
        } catch (e: IOException) {
            null
        } catch (e: Exception) {
            null
        }


        //el sigiente paso se usa para salvar la informacion dentro de la base de datos
        response?.let {
            //codigo sin base de datos
            //_breedList.value = it.getBreeds()

            //codigo con base de datos
            withContext(Dispatchers.IO){
                breedDatabase.getDogDao().insertAll(it.getBreeds().map{it.toLocal()})
            }

        }
    }







    suspend fun loadRandomImage(breed: Breed) {
        val result = try {
            if (breed.subBreed.isEmpty()) {
                apiService.getRandomImage(breed.breed)
            } else {
                apiService.getRandomImage(breed.breed, breed.subBreed)
            }
        } catch (e: HttpException) {
            null
        } catch (e: IOException) {
            null
        } catch (e: Exception) {
            null
        }

        result?.let {
            _randomImage.value = it.url
        }
    }

   suspend fun updateBreedInfo(breed: Breed) {
        withContext(Dispatchers.IO){
            breedDatabase.getDogDao().update(breed.toLocal())
        }
    }

}