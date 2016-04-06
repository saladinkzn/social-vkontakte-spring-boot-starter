# social-vkontakte-spring-boot-starter
[![Build Status](https://travis-ci.org/saladinkzn/social-vkontakte-spring-boot-starter.svg?branch=master)](https://travis-ci.org/saladinkzn/social-vkontakte-spring-boot-starter)
[![License](http://img.shields.io/badge/license-MIT-47b31f.svg)](#copyright-and-license)

Spring boot starter for spring social vkontakte

Usage:
-------------------------------------------------

Add following snippet to your project:
```groovy
repositories {
    maven { url 'http://oss.jfrog.org/artifactory/oss-release-local' }
    maven { url 'http://fugru.com/archiva/repository/snapshots' }

}

dependencies {
    compile 'ru.shadam.spring-boot:social-vkontakte-spring-boot-starter:0.1'
}
```

Add following properties to your SpringBoot application:
```
ru.shadam.social-vkontakte.client-id=//ClientId
ru.shadam.social-vkontakte.client-secret=//ClientSecret
```

This project has [a showcase](https://github.com/saladinkzn/social-vkontakte-spring-boot-starter-showcase) now.

How to build it:
------------------------------------------------------
Checkout the repo then use gradle wrapper script (gradlew or gradlew.bat)
