package com.lazaroofarrill.ktor_lab.framework

import com.lazaroofarrill.ktor_lab.framework.interfaces.IController

data class Module(
    val controllers: List<IController> = emptyList(),
    val prefix: String? = null
)



