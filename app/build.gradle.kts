plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
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
    implementation("com.squareup.retrofit2:retrofit:2.11.0") //*************
    implementation("com.squareup.retrofit2:converter-gson:2.11.0") //  Dependencias para usar retrofit (API)
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0") //*********
    implementation("org.mariadb.jdbc:mariadb-java-client:2.4.1") //LIBRERIA PARA EL CONECTOR DE MARIADB
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4") //LINEA QUE IMPLEMENTA FUNCIONES DE JAVA PARA EVITAR CRASHEOS
}