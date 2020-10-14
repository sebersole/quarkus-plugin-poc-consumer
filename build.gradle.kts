buildscript {
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // this part is just for personal plugin SNAPSHOT testing.
    // generally users would not need this part
    configurations {
        classpath {
            resolutionStrategy {
                cacheChangingModulesFor(0, java.util.concurrent.TimeUnit.SECONDS )
            }
        }
    }
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath( enforcedPlatform( "io.quarkus:quarkus-bom:1.7.1.Final" ) )

        // would be nicer to name the deployment artifact and have a way to map
        // that to the corresponding runtime artifact.  however, the way this works
        // in Quarkus at the moment is the other way around.
        //classpath 'io.quarkus:quarkus-hibernate-orm-deployment'
        classpath( "io.quarkus:quarkus-hibernate-orm" )
    }
}

plugins {
    `java-library`
    id( "com.dorongold.task-tree" ) version "1.5"
    id( "sebersole.quarkus.plugin-poc" ) version "0.9"
}

group = "org.hibernate.build.gradle"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}


dependencies {
    val junit5Version = "5.3.1"

    implementation( "javax.persistence:javax.persistence-api:2.2" )

    testImplementation( "org.junit.jupiter:junit-jupiter-api:${junit5Version}" )
    testRuntimeOnly( "org.junit.jupiter:junit-jupiter-engine:${junit5Version}" )
}

quarkus {
    hibernateOrm {

    }
    dataSources {
        setDatabaseKind( "derby" )
    }
    jpa {
        persistenceUnits {
            create( "pu-abc" ) {
            }
        }
    }
}