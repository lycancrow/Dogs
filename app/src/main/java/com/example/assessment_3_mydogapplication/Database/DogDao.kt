package com.example.assessment_3_mydogapplication.Data.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.assessment_3_mydogapplication.Database.DbModel.LocalBreed

//este es como el API SERVICE DE LA BASE DE DATOS LOCAL
@Dao
interface DogDao {

    @Query("SELECT * FROM local_breeds")
    fun getBreedInfos(): LiveData<List<LocalBreed>>//ESTO RETORNA EL RESULTADO EN FORMA DE
                                                    // LIVEDATA ES LA MEJOR FORMA DE HACERLO



    @Query("Select * FROM local_breeds WHERE isFaved=1 ")
    fun getFavedBreeds():LiveData<List<LocalBreed>>

    //insertar en la base de datos
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(breeds: List<LocalBreed>)//lo que esta entre parentesis DEL @Insert le dice al programa
                                            //que hacer en caso de que una fila tenga el mismo nombre
                                            // que una que ya existe en la base de datos.
                                            //estas opciones son (IGNORE, APBORT, REPLACE, FAIL, ROLLBACK)

                                            // del mismo modo, la funcion insertAll dice que coja los elementos de la
                                            // varible breeds de tipo List<Localbreed>
                                            // a modo de Lista, y los ponga en la base de datos

    @Update
    fun update(breed: LocalBreed)

}