import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.dsl.RepositoryHandler


fun DependencyHandler.dependency(notation: Any, config: ExternalModuleDependency.() -> Unit): Dependency {
    val model = module(notation)
    if (model is ExternalModuleDependency) {
        config(model)
        return model
    } else {
        throw IllegalArgumentException("Notation is not external module")
    }
}

fun ExternalModuleDependency.exclude(group: String? = null, module: String? = null) {
    val excludeNotation = mapOf("group" to group, "module" to module).filterValues { it != null }
    exclude(excludeNotation)
}

fun RepositoryHandler.maven(url: String) {
    maven {
        it.setUrl(url)
    }
}