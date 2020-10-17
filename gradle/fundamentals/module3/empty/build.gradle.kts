plugins { 
    java 
    id("org.flywaydb.flyway") version "6.3.2"
}

tasks.register("hello") {
    doFirst {
        print("Hello")
    }
}

tasks.register("world") {
    
    dependsOn("hello")

    doLast {
        println(" World")
    }
}