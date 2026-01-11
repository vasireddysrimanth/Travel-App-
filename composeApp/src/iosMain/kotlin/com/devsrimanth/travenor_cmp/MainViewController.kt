package com.devsrimanth.travenor_cmp

import androidx.compose.ui.window.ComposeUIViewController
import com.devsrimanth.travenor_cmp.di.appModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController(
     configure = {
         startKoin { modules(appModule) }
     }
) { App() }