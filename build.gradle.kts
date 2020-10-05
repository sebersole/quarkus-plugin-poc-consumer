plugins {
    `java-library`
    id( "com.dorongold.task-tree" ) version "1.5"
    id( "sebersole.quarkus.plugin-poc" ) version "0.2"
}

group = "org.hibernate.build.gradle"
version = "1.0.0-SNAPSHOT"

val quarkusVersion = "1.7.1.Final"

repositories {
    mavenCentral()
}


dependencies {
    val junit5Version = "5.3.1"

    implementation( "javax.persistence:javax.persistence-api:2.2" )

    // open question whether to auto add this one
    quarkusPlatforms( enforcedPlatform( "io.quarkus:quarkus-universe-bom:${quarkusVersion}" ) )

    testImplementation( "org.junit.jupiter:junit-jupiter-api:${junit5Version}" )
    testRuntimeOnly( "org.junit.jupiter:junit-jupiter-engine:${junit5Version}" )
}

quarkus {
    var quarkusVersion = "1.7.1.Final"

    platform( "io.quarkus:quarkus-universe-bom:${quarkusVersion}" )

    extensions {
        // specialized creations

        hibernateOrm {
            databaseFamily.set( "derby" )

            persistenceUnits {
                create( "abc" ) {
                    include( project( ":" ) )
                }
            }
        }

        extension( "custom2" ) {
            artifact( "org.junit.jupiter:junit-jupiter-api:5.3.1" )
        }

        quarkusExtension( "hibernate-validator" )
    }
}

