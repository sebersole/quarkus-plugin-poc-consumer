rootProject.name = "quarkus-plugin-poc-consumer"

// mostly a lot of magic to be able to consumer SNAPSHOT versions of the plugin...

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven {
            name = "jboss-snapshots-repository"
            url = uri( "https://repository.jboss.org/nexus/content/repositories/snapshots" )
        }
    }

    resolutionStrategy {
        eachPlugin {
            if ( requested.id.namespace == "sebersole.quarkus"
                    && requested.id.name == "plugin-poc" ) {
                if ( requested.version.orEmpty().endsWith("-SNAPSHOT" )) {
                    useModule("com.github.sebersole:quarkus-gradle-poc-plugin:0.1-SNAPSHOT")
                }
            }
        }
    }
}