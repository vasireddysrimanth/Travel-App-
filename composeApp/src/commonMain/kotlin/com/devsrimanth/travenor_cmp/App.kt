package com.devsrimanth.travenor_cmp

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.devsrimanth.presentation.feature.app.AppViewModel
import com.devsrimanth.travenor_cmp.navigation.TrevnorNavRoot
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App(viewModel: AppViewModel = koinViewModel()) {
    MaterialTheme {
        val uiSource = viewModel.state.collectAsState()
        if (!uiSource.value.isLoading) {
            TrevnorNavRoot(uiSource.value.authToken)
        } else {
            CircularProgressIndicator()
        }
    }
}

/**
 * we follow clean architecture for this
 * 1.data  - > will depends on domain
 * 2. domain -> its independent of data
 * 3.presentation
 *  main(compose app ) -> it will manage all di and IOS and Android Shared Code
 */

/**
 * we create a module for managing all sharing based on clean architecture
 *
 */