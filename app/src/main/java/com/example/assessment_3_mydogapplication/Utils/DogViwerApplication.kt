package com.example.assessment_3_mydogapplication.Utils

import android.app.Application
import com.example.assessment_3_mydogapplication.Core.ServiceLocator

class DogViewerApplication: Application() {
    //esto es el codigo de esta clase sin base de datos
    /*val serviceLocator: ServiceLocator = ServiceLocator()*/


    //este es el codigo de esta clase con base de datos

    //el lateinit permite declarar una variable sin especificar sin usar un valor u opcional pero se debe asegurar que
    //esa variable siempre tendra un valor cuando es llamado
    lateinit var serviceLocator: ServiceLocator

    //teniendo en cuenta eso, se crea esta funcion en donde se dice basicamente que se van a crear en la variable serviceLocator
    //los valores del ServiceLocator con el contexto de la aplicacion. se debe hacer asi porque el application context es dinamico
    //y no carga valores de un solo tipo
    override fun onCreate() {
        super.onCreate()
        serviceLocator = ServiceLocator(applicationContext)
    }

}