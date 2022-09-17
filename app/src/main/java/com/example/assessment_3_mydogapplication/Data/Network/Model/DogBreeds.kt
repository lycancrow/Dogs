package com.example.assessment_3_mydogapplication.Data.Network.Model

import com.example.assessment_3_mydogapplication.Data.Network.Domain.Breed
import com.example.assessment_3_mydogapplication.Database.DbModel.LocalBreed
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BreedList(
    @Json(name = "message")
    val breedsMap: Map<String, List<String>>
) {

    fun getBreeds(): List<Breed> {
        val result = mutableListOf<Breed>()

        for ((breed, subBreeds) in breedsMap) {
            if (subBreeds.isEmpty()) {
                result.add(Breed(breed,isFaved = false,urlBreed = null))
            } else {
                subBreeds.forEach { subBreed ->
                    result.add(Breed(breed, subBreed, isFaved = false,urlBreed = null))
                }
            }
        }

        result.sort()
        return result

    }

}

@JsonClass(generateAdapter = true)
data class RandomImage(
    @Json(name = "message")
    val url: String
)