package com.example.assessment_3_mydogapplication.Data.Network

import com.example.assessment_3_mydogapplication.Data.Network.Model.BreedList
import com.example.assessment_3_mydogapplication.Data.Network.Model.RandomImage
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("breeds/list/all")
    suspend fun getBreed(): BreedList

    @GET("breed/{breed_name}/{sub_breed_name}/images/random")
    suspend fun getRandomImage(@Path("breed_name") breedName: String,
                               @Path("sub_breed_name") subBreedName: String): RandomImage

    @GET("breed/{breed_name}/images/random")
    suspend fun getRandomImage(@Path("breed_name") breedName: String): RandomImage
}