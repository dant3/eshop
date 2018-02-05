
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.dsl.RepositoryHandler

class Dependencies(private val dependencies: DependencyHandler, private val repositories: RepositoryHandler) {
    constructor(project: Project): this(project.dependencies, project.repositories)

    init {
        with (repositories) {
            mavenCentral()
            maven("https://maven.google.com")
            maven("https://dl.bintray.com/jetbrains/anko")
            maven("https://kotlin.bintray.com/kotlinx")
            maven("https://dl.bintray.com/lapism/maven")
            maven("https://google.bintray.com/flexbox-layout")
        }
    }

    val kotlinStdlib = listOf(
            "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}",
            "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    )
    val appCompat = listOf(
            "com.android.support:support-v4:${Versions.appCompat}",
            "com.android.support:support-annotations:${Versions.appCompat}",
            "com.android.support:appcompat-v7:${Versions.appCompat}",
            "com.android.support:cardview-v7:${Versions.appCompat}",
            "com.android.support:design:${Versions.appCompat}",
            "com.android.support:recyclerview-v7:${Versions.appCompat}",
            "com.android.support:percent:${Versions.appCompat}"
    )
    val junit = "junit:junit:4.12"
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    val mockwebserver = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val jacksonDatabind = listOf(
            "com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.jacksonVersion}",
            "com.fasterxml.jackson.core:jackson-databind:${Versions.jacksonVersion}"
    )
    val liveData = listOf(
            "android.arch.lifecycle:extensions:1.0.0-rc1"
    )
    val stetho = listOf(
            "com.facebook.stetho:stetho:${Versions.stethoVersion}",
            "com.facebook.stetho:stetho-js-rhino:${Versions.stethoVersion}",
            "com.facebook.stetho:stetho-okhttp3:${Versions.stethoVersion}"
    )
    val multiDex = "com.android.support:multidex:1.0.1"
    val searchView = "com.lapism:searchview:5.0.0-alpha7"
    val flexbox = "com.google.android:flexbox:0.3.1"
    val anko = listOf(
            "org.jetbrains.anko:anko-commons:${Versions.anko}",
            "org.jetbrains.anko:anko-coroutines:${Versions.anko}"
    )

    val espresso = dependencies.dependency("com.android.support.test.espresso:espresso-core:2.2.2") {
        exclude(group = "com.android.support", module = "support-annotations")
    }
}