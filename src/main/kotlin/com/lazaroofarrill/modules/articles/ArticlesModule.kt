package com.lazaroofarrill.modules.articles

import com.lazaroofarrill.framework.Module
import com.lazaroofarrill.modules.articles.controllers.articleController

val articlesModule = Module(controllers = listOf(articleController))

