package org.sample.preferencesapp.core.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppInputTextField(
    modifier: Modifier = Modifier,
    label: (@Composable () -> Unit)? = null,
    placeHolder: (@Composable () -> Unit)? = null,
    value: String,
    maxLines: Int = 1,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    Column(
        modifier = modifier
    ) {
        label?.let { label ->
            label()
            Spacer(Modifier.height(5.dp))
        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth()
                .defaultMinSize(minWidth = 56.dp),
            value = value,
            onValueChange = onValueChange,
            placeholder = placeHolder,
            maxLines = maxLines,
            singleLine = maxLines == 1,
            keyboardOptions = keyboardOptions,
            textStyle = MaterialTheme.typography.bodyLarge
        )
    }
}