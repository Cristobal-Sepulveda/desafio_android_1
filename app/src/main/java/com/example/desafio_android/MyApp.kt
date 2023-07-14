package com.example.desafio_android

import android.app.Application
import com.example.desafio_android.data.AppDataSource
import com.example.desafio_android.data.AppRepository
import com.example.desafio_android.ui.details.DetailsViewModel
import com.example.desafio_android.ui.home.HomeViewModel
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val myModule = module {
            viewModel{
                HomeViewModel(
                    get() as AppDataSource
                )
            }
            //single {
            //    HomeViewModel(
            //        get() as AppDataSource
            //    )
            //}

            single {
                DetailsViewModel(
                    get() as AppDataSource
                )
            }

            //REPOSITORY
            single { AppRepository() as AppDataSource }
        }

        startKoin {
            androidContext(this@MyApp)
            modules(listOf(myModule))
        }
    }
}








// errores con un punto especifico en live data emite el resultado que esta guardado
// el observador se recrea cuando vuelve a la vista, tener cuidado que cosas coloco
// en el observador xq todo eso se va a ejecutar cuando entre a la vista.



//contenedoress de viewmodel -> koin -> viewModel ->
// los viewModel se declaran con ViewModel, no con single


//se ingresa nivel 13 -> cuando paso el año paso al 12 junion, 11 y 10 semi senior


// Arquitectura es el ambiente -> clean con mvp como la app maneja la logica, como interactuan, la base de datos, como organizamos la arquitectura
// patrones de diseño -> son herramientas, por ejemplo singleton que se utilizan para construir la arquitectura. son los legitos que usamos para construir la arquitectura
// patrones de presentación -> MVVM, MVC, MV solo afecta a la vista y como se conecta con la base de datos como -> mvvm es con livedata, si tengo mvi puede ser otra la manera en que se optiene la información


// retrofit en el banco, es un cliente modificado,

// glide para la imagenes, es parecido a picasso.

// los viewmodel se conectan directamente a los services.

// balance 70% es el limite para los testing.

// multimodular -> modulo. la carpeta app es un modulo


// la app se puede separar en distintas modulos


// modulo base es todo lo que es transversal a la app
// multimodular, itau -> los modulos estan separados por features, module de inversiones tiene muchas features


// gradle -> el codigo es groovy

// 70% es el limite para los testing
// para el test se utiliza: unitarios: Junit,
// para interfaz gratis: Espresso
// integración: JUnit. _> que dos cosas interactuen bien entre si, etc.


// puedo agregar la ruta de git de mi repositorio como origen de ese proyecto, git remote add .git
// git remote add origin xxxx.git















