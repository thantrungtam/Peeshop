plugins {
    alias(libs.plugins.androidApplication)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
        buildFeatures {
            viewBinding = true
        }
    }
    buildFeatures {
        viewBinding = true
    }

    dependencies {

        implementation(libs.appcompat)
        implementation(libs.material)
        implementation(libs.activity)
        implementation(libs.constraintlayout)
        implementation(libs.annotation)
        implementation(libs.lifecycle.livedata.ktx)
        implementation(libs.lifecycle.viewmodel.ktx)
        implementation(libs.firebase.auth)
        implementation(libs.firebase.database)
        implementation(libs.firebase.storage)
        testImplementation(libs.junit)
        androidTestImplementation(libs.ext.junit)
        androidTestImplementation(libs.espresso.core)
        implementation("com.github.bumptech.glide:glide:4.12.0")
        implementation("com.google.code.gson:gson:2.9.1")
        annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")
        implementation("androidx.viewpager2:viewpager2:1.0.0-beta02")
        implementation("com.makeramen:roundedimageview:2.3.0")
        implementation ("com.google.android.material:material:1.2.0")



    }
}
dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.annotation)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.activity)
}
