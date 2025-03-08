package com.cosmicstruck.linkspehere.profileBuilding.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun AutocompleteTextField(
    suggestions: List<String> = listOf<String>("AP SHAH COLLEGE","BHARATI VIDYAPEETH","DY PATIL","FRANCIS INSTITUTE OF TECHNOLOGY"),
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var userInput by remember { mutableStateOf("") }
    var showDropdown by remember { mutableStateOf(false) }
    var isValid by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = userInput,
            onValueChange = { input ->
                userInput = input
                showDropdown = input.isNotEmpty()
                isValid = false // Reset validation until an item is selected
            },
            label = { Text("Enter College Name") },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .focusRequester(focusRequester),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )

        if (showDropdown) {
            DropdownMenu(
                expanded = showDropdown,
                onDismissRequest = { showDropdown = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                val filteredSuggestions = suggestions.filter { it.contains(userInput, ignoreCase = true) }
                if (filteredSuggestions.isEmpty()) {
                    DropdownMenuItem(
                        text = { Text("No matches found") },
                        onClick = {
                            showDropdown = false
                        }
                    )
                } else {
                    filteredSuggestions.forEach { suggestion ->
                        DropdownMenuItem(
                            text = { Text(suggestion) },
                            onClick = {
                                userInput = suggestion
                                showDropdown = false
                                isValid = true
                                onItemSelected(suggestion)
                                focusManager.clearFocus()
                            }
                        )
                    }
                }
            }
        }

        // Validation message
        if (userInput.isNotEmpty() && !isValid) {
            Text(
                text = "Please select an item from the list",
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        LaunchedEffect(showDropdown) {
            if (showDropdown) {
                focusRequester.requestFocus()
            }
        }
    }
}