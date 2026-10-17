plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.canopy.compiler)
    application
}

dependencies {
    implementation(libs.canopy.engine)
    implementation(libs.canopy.terminal)
    runtimeOnly(libs.logback)
}

kotlin { jvmToolchain(25) }

application { mainClass.set("MainKt") }

tasks.withType<JavaExec>().configureEach {
    jvmArgs("--enable-native-access=ALL-UNNAMED")
}
