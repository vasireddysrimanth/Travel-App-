package com.devsrimanth.travenor_cmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform