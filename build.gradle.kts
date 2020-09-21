import com.github.sebersole.gradle.quarkus.extension.orm.HibernateOrmExtension;

plugins {
    id("java" )
    id("sebersole.quarkus.plugin-poc" )
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
    quarkusVersion = "1.7.1.Final"

    platform( "io.quarkus:quarkus-universe-bom:${quarkusVersion}" )

    extensions {
        // specialized creations

        create( "hibernateOrm", HibernateOrmExtension::class ) {
            databaseFamily = "derby"

            persistenceUnits {
                create( "abc" )
            }
        }

        extension( "custom2" ) {
            artifact("org.junit.jupiter:junit-jupiter-api:5.3.1" )
        }

        quarkusExtension( "hibernate-validator" )
    }
}

// todo : handle this in the plugin
//tasks.compileJava.get().classpath += quarkus.runtimeDependencies

