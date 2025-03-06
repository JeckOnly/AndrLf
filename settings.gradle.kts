pluginManagement {
    repositories {
        // LAN
        maven {
            isAllowInsecureProtocol = true
            url = uri("http://192.168.10.60:8081/nexus/repository/public")
        }
        maven {
            isAllowInsecureProtocol = true
            url = uri("http://192.168.10.60:8081/nexus/repository/central")
        }

        maven {
            isAllowInsecureProtocol = true
            url = uri("http://192.168.10.60:8081/nexus/repository/jitpack/")
        }

        maven {
            isAllowInsecureProtocol = true
            url = uri("http://192.168.10.60:8081/nexus/repository/google")
        }

        maven {
            isAllowInsecureProtocol = true
            url = uri("http://192.168.10.60:8081/nexus/repository/maven-public/")
        }
        maven {
            isAllowInsecureProtocol = true
            url = uri("http://192.168.10.60:8081/nexus/repository/android-group-snapshot")
        }
        maven {
            isAllowInsecureProtocol = true
            url = uri("http://192.168.10.60:8081/nexus/repository/android-group")
        }
        mavenLocal()
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // LAN
        maven {
            isAllowInsecureProtocol = true
            url = uri("http://192.168.10.60:8081/nexus/repository/public")
        }
        maven {
            isAllowInsecureProtocol = true
            url = uri("http://192.168.10.60:8081/nexus/repository/central")
        }

        maven {
            isAllowInsecureProtocol = true
            url = uri("http://192.168.10.60:8081/nexus/repository/jitpack/")
        }

        maven {
            isAllowInsecureProtocol = true
            url = uri("http://192.168.10.60:8081/nexus/repository/google")
        }

        maven {
            isAllowInsecureProtocol = true
            url = uri("http://192.168.10.60:8081/nexus/repository/maven-public/")
        }
        maven {
            isAllowInsecureProtocol = true
            url = uri("http://192.168.10.60:8081/nexus/repository/android-group-snapshot")
        }
        maven {
            isAllowInsecureProtocol = true
            url = uri("http://192.168.10.60:8081/nexus/repository/android-group")
        }
        maven {
            isAllowInsecureProtocol = true
            url = uri("http://192.168.10.60:8081/nexus/repository/tencent-maven/")
        }
        maven {
            isAllowInsecureProtocol = true
            url = uri("http://192.168.10.60:8081/nexus/repository/AwemeOpenSDK/")
        }
        maven {
            isAllowInsecureProtocol = true
            url = uri("http://192.168.10.60:8081/nexus/repository/pangle/")
        }
        maven {
            isAllowInsecureProtocol = true
            url = uri("http://192.168.10.60:8081/nexus/repository/Volcengine/")
        }
        mavenLocal()
        google()
        mavenCentral()
    }
}

rootProject.name = "AndrLf"
include(":app")
include(":processor")
