import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    kotlin("android")
}

// Load keystore properties
val keystorePropertiesFile = rootProject.file("app/keystore.properties")
val keystoreProperties = Properties()
if (keystorePropertiesFile.exists()) {
    keystoreProperties.load(FileInputStream(keystorePropertiesFile))
}

android {
    namespace = "org.opstree.app"
    compileSdk = 35 // Update to your version
    
    defaultConfig {
        applicationId = "org.opstree.app"
        minSdk = 32 // Update to your version
        targetSdk = 35 // Update to your version
        versionCode = 5
        versionName = "1.0.1"
    }
    
    //signingConfigs {
    //    create("release") {
    //        storeFile = file(keystoreProperties.getProperty("storeFile") ?: "upload-keystore.jks")
    //        storePassword = keystoreProperties.getProperty("storePassword")
    //        keyAlias = keystoreProperties.getProperty("keyAlias")
    //        keyPassword = keystoreProperties.getProperty("keyPassword")
    //    }
    //}
    
    buildTypes {
        release {
            // signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }
    
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")
    implementation("org.apache.commons:commons-text:1.11.0")
    implementation(project(":utilities"))
}
