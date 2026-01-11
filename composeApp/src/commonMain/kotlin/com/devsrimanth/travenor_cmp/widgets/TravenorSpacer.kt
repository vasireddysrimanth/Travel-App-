package com.devsrimanth.travenor_cmp.widgets

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp


/**
 * common spacer for entire app
 */
@Composable
fun TravenorSpacer(size: Dp) {
    Spacer(modifier = Modifier.size(size))
}