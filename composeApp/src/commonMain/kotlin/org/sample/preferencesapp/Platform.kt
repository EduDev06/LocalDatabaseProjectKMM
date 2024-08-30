package org.sample.preferencesapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform