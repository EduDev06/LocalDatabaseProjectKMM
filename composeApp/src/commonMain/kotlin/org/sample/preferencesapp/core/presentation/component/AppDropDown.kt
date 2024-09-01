package org.sample.preferencesapp.core.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun <T> AppDropDown(
    modifier: Modifier = Modifier,
    label: (@Composable () -> Unit)? = null,
    selectedOption: String,
    options: List<T>,
    onOptionSelected: (T) -> Unit,
    shape: CornerBasedShape = MaterialTheme.shapes.medium
) {
    var expanded by remember { mutableStateOf(false) }
    Column {
        label?.let { label ->
            label()
            Spacer(modifier = Modifier.height(4.dp))
        }

        Box(
            modifier = modifier
                .height(56.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground,
                    shape = shape
                )
                .clip(shape)
                .clickable {
                    expanded = expanded.not()
                },
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 8.dp,
                        horizontal = 12.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = selectedOption)
                Icon(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(24.dp),
                    imageVector = if (expanded) {
                        Icons.Filled.KeyboardArrowUp
                    } else {
                        Icons.Filled.KeyboardArrowDown
                    },
                    contentDescription = null
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { selectedOption ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = selectedOption.toString(),
                                style = MaterialTheme.typography.titleSmall
                            )
                        },
                        onClick = {
                            onOptionSelected(selectedOption)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}