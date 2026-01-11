package com.devsrimanth.travenor_cmp.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.devsrimanth.presentation.feature.details.TravelListingDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun TravelItemDetailsScreen(
    backStack: NavBackStack<NavKey>,
    itemId: String,
    viewModel: TravelListingDetailsViewModel = koinViewModel {
        parametersOf(itemId)
    }
) {
    val uiState = viewModel.state.collectAsState()

    Scaffold {
        Column(modifier = Modifier.padding(it)) {
            Text("Travel Item Details Screen for $itemId")
        }
    }
}