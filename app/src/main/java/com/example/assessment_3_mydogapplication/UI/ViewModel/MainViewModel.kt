package com.example.assessment_3_mydogapplication.UI.ViewModel

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.example.assessment_3_mydogapplication.Data.Repository.DogRepository
import com.example.assessment_3_mydogapplication.Data.Network.Domain.Breed
import com.example.assessment_3_mydogapplication.Keys.BundleKeys
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class MainViewModel(
    private val repository: DogRepository,
    private val state: SavedStateHandle
    ) : ViewModel() {

    private val mainTab: MainTab= state.get(BundleKeys.MAIN_TAB) ?: MainTab.All
    val breedList = when (mainTab){
        MainTab.All -> repository.breedList
        MainTab.Faved -> repository.favedBreedInfos
    }



    private val _navigateToDetails = MutableLiveData<Breed?>()
    val navigateToDetails: LiveData<Breed?> get() = _navigateToDetails

    init {
        viewModelScope.launch {
            repository.loadDogList()
        }
    }

    fun onBreedClicked(breed: Breed) {
        _navigateToDetails.value = breed
    }

    fun onNavigateToDetailFinished() {
        _navigateToDetails.value = null
    }

    fun onFaveClicked(breed: Breed) {
        breed.isFaved = !breed.isFaved
        viewModelScope.launch {
            repository.updateBreedInfo(breed)
        }
    }
}

class MainViewModelFactory(private val repository: DogRepository,
                           owner: SavedStateRegistryOwner,
                           defaultArgs: Bundle?
): AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository, handle) as T
        }

        throw IllegalArgumentException("Invalid View Model")
    }

}

