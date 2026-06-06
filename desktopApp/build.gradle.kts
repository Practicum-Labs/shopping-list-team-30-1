import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

dependencies {
    implementation(projects.shared)
    implementation(compose.desktop.currentOs)
    implementation(libs.kotlinx.coroutines.swing)
    implementation(libs.compose.ui.tooling.preview )
    implementation(libs.ktor.client.okhttp)
    implementation(libs.nbvcxz)
    implementation(libs.koin.core)
}

compose.desktop {
    application {
        mainClass = "io.dimasla4ee.shoppinglist.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "io.dimasla4ee.shoppinglist"
            packageVersion = "1.0.0"
        }
    }
}