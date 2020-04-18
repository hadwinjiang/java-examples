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