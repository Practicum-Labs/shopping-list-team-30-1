import dev.detekt.gradle.Detekt
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.detekt)
    alias(libs.plugins.ktorfit)
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    ignoreFailures = true
    autoCorrect = false

    config.setFrom(files("$rootDir/config/detekt/detekt.yml"))

    source.setFrom(
        "src/commonMain/kotlin",
        "src/androidMain/kotlin",
        "src/jvmMain/kotlin"
    )
}

tasks.withType<Detekt>().configureEach {
    jvmTarget = JvmTarget.JVM_11.target

    reports {
        html.required.set(true)
        markdown.required.set(true)
        sarif.required.set(true)
    }
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm()

    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.core.ktx)
            implementation(libs.androidx.appcompat)
            implementation(libs.androidx.activity.compose)

            implementation(libs.ktor.client.okhttp)

            implementation(libs.koin.android)

            implementation("me.gosimple:nbvcxz:1.5.1")
        }
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)

            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled.lib)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.kotlinx.serialization.json)

            implementation(libs.navigation3.ui)
            implementation(libs.lifecycle.viewmodel.navigation3)

            implementation(libs.kotlinx.coroutines)
            implementation(libs.kotlinx.coroutines.test)

            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.test.junit4)
            implementation(libs.koin.compose.viewmodel)

            implementation(libs.compose.components.resources)
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.ktorfit)

            implementation(libs.reorderable)

            api(libs.androidx.datastore)
            api(libs.androidx.datastore.preferences)
        }
        commonTest.dependencies {
            implementation(libs.junit)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.ktor.client.okhttp)

            implementation("me.gosimple:nbvcxz:1.5.1")

            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.10.2")
        }
    }
}

android {
    namespace = "io.dimasla4ee.shoppinglist"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "io.dimasla4ee.shoppinglist"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    testImplementation(libs.jupiter.junit.jupiter)
    // Room KSP
    add("kspAndroid", libs.room.compiler)
    add("kspJvm", libs.room.compiler)

    // Ktor
    add("kspCommonMainMetadata", libs.ktorfit.ksp)
    add("kspAndroid", libs.ktorfit.ksp)
    add("kspJvm", libs.ktorfit.ksp)

    // Compose debug tools
    debugImplementation(libs.compose.ui.tooling)
    detektPlugins(libs.detekt.compose.rules)
}

compose.desktop {
    application {
        mainClass = "io.dimasla4ee.shoppinglist.app.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "io.dimasla4ee.shoppinglist"
            packageVersion = "1.0.0"
        }
    }
}
