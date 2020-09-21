rootProject.name = "quarkus-plugin-poc-consumer"

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven {
            name = "jboss-snapshots-repository"
            url = uri( "https://repository.jboss.org/nexus/content/repositories/snapshots" )
        }
    }
    resolutionStrategy {
        var pluginVersion = "0.1-SNAPSHOT"

        eachPlugin {
            if ( requested.id.namespace == "sebersole.quarkus"
                    && requested.id.name == "plugin-poc" ) {
                useModule("com.github.sebersole:quarkus-gradle-poc-plugin:${pluginVersion}")

            }
        }
    }
}