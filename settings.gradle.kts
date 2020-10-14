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
            if ( requested.id.namespace == "sebersole.quarkus" && requested.id.name == "plugin-poc-buildscript" ) {
                if ( requested.version.orEmpty().endsWith("-SNAPSHOT" )) {
                    val notation = "com.github.sebersole.quarkus:quarkus-poc-buildscript-ext:${requested.version}"
                    logger.lifecycle( "Swapping SNAPSHOT version of plugin : {}", notation )
                    useModule(notation)
                }
            }
        }
    }
}