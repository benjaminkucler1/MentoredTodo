package me.benjamin.mentoredtodo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform