plugins {
    alias(libs.plugins.android.application)
    //alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp) //ksp para room
}

android {
    namespace = "com.example.appalimentos"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.appalimentos"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true //CAMBIO AQUI
        sourceCompatibility = JavaVersion.VERSION_1_8 //CAMBIA LA VERSION DE JAVA PARA COMPATIBILIDAD CON MARIADB
        targetCompatibility = JavaVersion.VERSION_1_8 //CAMBIA LA VERSION DE JAVA PARA COMPATIBILIDAD CON MARIADB
    }
    buildFeatures {
        compose = true
    }

    //MODIFICACION 05-06-2026
    //EVITA ERRORES DE DUPLICACION
    packaging{
        resources{
            excludes += "/META-INF/NOTICE.md"
            excludes += "/META-INF/LICENSE.md"
            excludes += "/META-INF/LICENSE-notice.md"
        }
    }

}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    implementation("androidx.navigation:navigation-compose:2.8.0")// navegacion
    implementation("androidx.room:room-runtime:2.7.2")// Room
    implementation("androidx.room:room-ktx:2.7.2")///
    ksp("androidx.room:room-compiler:2.7.2")//**********
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.1") // Dependencias
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.9.1") //   Para ViewModel
    implementation("com.squareup.retrofit2:retrofit:2.11.0") //*************
    implementation("com.squareup.retrofit2:converter-gson:2.11.0") //  Dependencias para usar retrofit (API)
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0") //*********
    implementation("com.sun.mail:jakarta.mail:2.0.1")//LIBRERIA PARA ENVIO DE CORREOS
    implementation("org.mindrot:jbcrypt:0.4")//LIBRERIA PARA ENCRIPTAR CONTRASEÑAS
    implementation("org.mariadb.jdbc:mariadb-java-client:2.4.1") //LIBRERIA PARA EL CONECTOR DE MARIADB
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4") //LINEA QUE IMPLEMENTA FUNCIONES DE JAVA PARA EVITAR CRASHEOS
}


