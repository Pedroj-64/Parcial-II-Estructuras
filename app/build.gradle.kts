plugins {
    // Permite ejecutar la aplicación con ./gradlew run
    application
}

repositories {
    mavenCentral()
}

dependencies {
    // JUnit 5 (Jupiter) para las pruebas
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// Compilamos con Java 17, que es el mínimo pedido en el parcial
tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release = 17
}

application {
    mainClass = "parcial.ii.estructuras.App"
}

// La carpeta salida/ (dibujos SVG) queda en la raíz del repositorio,
// no dentro de app/, para que sea fácil encontrarla.
tasks.named<JavaExec>("run") {
    workingDir = rootProject.projectDir
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
