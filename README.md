# BetterFly DEMO

_Desafío:
Construir una app Android que cumpla con los siguientes requisitos:
● Tener una pantalla con un listado de ítems. La información del listado se debe
obtener desde algún servicio en cloud (sugerimos realizar el siguiente ejemplo
como API: https://rickandmortyapi.com/. Te pedimos que nos contactes
inmediatamente, antes de comenzar a realizar el desafío, en caso de que no
quieras utilizar tal ejemplo como guía (de modo que nos cuentes por qué no
quieres utilizar la API de R & M y cuál te gustaría usar).
● Tener una pantalla con el detalle del ítem. Cuando se selecciona un item desde el
listado, entra a la pantalla del detalle.
● Opcional: integrar Firebase con 1 o más de sus herramientas (ejemplo:
Authentication, Firestore, etc.).
La app se debe subir a algún repositorio para que los desarrolladores de Betterfly puedan
ver el código (y compilarlo si quieren).
*El lenguaje debe ser nativo, es decir Java o -preferentemente- Kotlin pero no hay
exigencias en cuanto a herramientas o librerías a utilizar.
El desarrollador puede usar lo que quiera de acuerdo a su comodidad._

## Screenshots 🚀


![Screen1](https://github.com/ezsocial/betterfly/blob/main/images/Screenshot_20210612_144100.png)

## Aqui para bajar la apk e instalarla en modo de PRUEBA en tu Smartphone 🚀
[![Download](https://api.bintray.com/packages/unsplash/unsplash-photopicker-android/com.unsplash.pickerandroid.photopicker/images/download.svg) ](https://github.com/ezsocial/betterfly/blob/main/betterflyDEMO.apk)
### Pre-requisitos 📋

_plugins {
    id 'com.android.application'
}

android {
    compileSdkVersion 30
    buildToolsVersion "30.0.3"

    defaultConfig {
        applicationId "net.ezmovil.betterflytest"
        minSdkVersion 23
        targetSdkVersion 30
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation 'androidx.appcompat:appcompat:1.3.0'
    implementation 'com.google.android.material:material:1.3.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    implementation 'androidx.navigation:navigation-fragment:2.3.5'
    implementation 'androidx.navigation:navigation-ui:2.3.5'
    testImplementation 'junit:junit:4.+'
    androidTestImplementation 'androidx.test.ext:junit:1.1.2'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'

    // http
    implementation 'org.jbundle.util.osgi.wrapped:org.jbundle.util.osgi.wrapped.org.apache.http.client:4.1.2'
    implementation 'com.github.clans:fab:1.6.4'
    implementation 'com.kofigyan.stateprogressbar:stateprogressbar:1.0.0'
    implementation "androidx.coordinatorlayout:coordinatorlayout:1.1.0"


    implementation 'androidx.appcompat:appcompat:1.0.0'
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.2.0'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0'
    //implementation 'io.github.yavski:fab-speed-dial:1.0.6'


    // For Card view
    implementation 'androidx.cardview:cardview:1.0.0'

    //implementation recyclerview
    implementation "androidx.recyclerview:recyclerview:1.1.0"
    implementation 'com.google.android.material:material:1.0.0-beta01'
    implementation 'com.google.code.gson:gson:2.8.5'

    // ButterKnife for view binding
    implementation 'com.jakewharton:butterknife:10.0.0'
    annotationProcessor 'com.jakewharton:butterknife-compiler:10.0.0'
    // Glide for image loading
    implementation 'com.github.bumptech.glide:glide:4.8.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.8.0'

    //fabs
    implementation 'com.google.android.material:material:1.1.0'
    implementation 'com.squareup.picasso:picasso:2.71828'


    implementation "commons-logging:commons-logging:1.2"


}
_

```
```

## Autores ✒️

_Sobre el desarrollador_

* **Juan Gomez** - *Software Enginner* - ezmovil.net@gmail.com

## Licencia 📄

Este proyecto está bajo la Licencia (Open) - 

## Gracias 🎁

* Gracias por permitirme participar en el proceso de seleccion a traves de Torre/Colombia 📢
