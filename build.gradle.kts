plugins {
    java
    id("org.springframework.boot") version "3.0.6"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "com.jk"
/*version = "0.1"*/
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter-web:3.0.6")
    implementation("org.springframework.boot:spring-boot-starter-data-rest:3.0.6")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.1")
    implementation("javax.servlet:jstl:1.2")
    implementation("org.apache.tomcat.embed:tomcat-embed-jasper:11.0.0-M5")     // jsp 사용가능
    implementation("com.google.guava:guava:31.1-jre")                           // code case 변환(CamelJSONObject)

    implementation("org.mybatis:mybatis:3.5.13")
    implementation("com.googlecode.json-simple:json-simple:1.1.1")
    implementation("commons-io:commons-io:2.11.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.15.0")

    runtimeOnly("mysql:mysql-connector-java:8.0.33")
    developmentOnly("org.springframework.boot:spring-boot-devtools:3.0.6")      // class파일 서버 재기동없이 수정

}

tasks.withType<Test> {
    useJUnitPlatform()
}
