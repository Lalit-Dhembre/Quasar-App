package com.cosmicstruck.linkspehere.profileBuilding.presentation

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cosmicstruck.linkspehere.profileBuilding.presentation.components.AutocompleteTextField
import com.cosmicstruck.linkspehere.profileBuilding.presentation.components.StylishProfilePhotoPicker
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileBuildingScreen(
    navigateToHomeScreen: ()-> Unit,
    viewModel: ProfileBuildingViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val state = viewModel.profileBuildingScreenState.value
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var showSuccessMessage by remember { mutableStateOf(false) }

    // Profile photo picker
    val profilePhotoPicker = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let {
            Log.d("PROFILE PHOTO", "$uri ADDED")
            viewModel.addProfilePhoto(uri)
        }
    }

    // Date picker for DOB
    val dobDatePicker = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val formattedDate = "$year-${month + 1}-$dayOfMonth"
            viewModel.addDOB(formattedDate)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).apply {
        datePicker.maxDate = calendar.timeInMillis
    }

    // Year picker for From Graduation
    val fromGraduationYearPicker = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, _, _ ->
            viewModel.addFromGraduation(year.toString())
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).apply {
        // Show only the year
        datePicker.updateDate(calendar.get(Calendar.YEAR), 0, 1)
    }

    // Year picker for To Graduation
    val toGraduationYearPicker = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, _, _ ->
            viewModel.addToGraduation(year.toString())
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).apply {
        // Show only the year
        datePicker.updateDate(calendar.get(Calendar.YEAR), 0, 1)
    }

    // Define Material 3 color scheme
    val colorScheme = MaterialTheme.colorScheme

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Build Your Profile",
                        fontWeight = FontWeight.Bold,
                        color = colorScheme.onSurface
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = colorScheme.surface
                )
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    if (viewModel.validateFields()) {
                        showSuccessMessage = true
                        Log.d("SUBMIT", "All fields are filled")
                    } else {
                        // Show error message
                        Log.d("SUBMIT", "Please fill all fields")
                        scope.launch{
                            showSnackbar(snackbarHostState = snackbarHostState, "Please fill all required fields")
                        }
                    }
                },
                containerColor = colorScheme.primaryContainer,
                contentColor = colorScheme.onPrimaryContainer,
                icon = { Icon(Icons.Filled.Check, contentDescription = "Submit") },
                text = { Text("Submit Profile") }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Profile Photo Picker
                StylishProfilePhotoPicker(
                    uri = state.profilePhotoUri,
                    onClick = {
                        profilePhotoPicker.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Profile Information Section
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = colorScheme.surfaceVariant.copy(alpha = 0.7f)
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 2.dp
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Personal Information",
                            style = MaterialTheme.typography.titleMedium,
                            color = colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.Bold
                        )

                        // First Name Input
                        EnhancedInputField(
                            placeholder = "First Name",
                            onValueChange = { viewModel.addFirstName(it) },
                            input = state.firstName,
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Person,
                                    contentDescription = "First Name"
                                )
                            }
                        )

                        // Last Name Input
                        EnhancedInputField(
                            placeholder = "Last Name",
                            onValueChange = { viewModel.addLastName(it) },
                            input = state.lastName,
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Person,
                                    contentDescription = "Last Name"
                                )
                            }
                        )

                        // Date of Birth Picker
                        OutlinedButton(
                            onClick = { dobDatePicker.show() },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = colorScheme.primary
                            )
                        ) {
                            Icon(
                                Icons.Default.DateRange,
                                contentDescription = "Date of Birth",
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(
                                text = if (state.dob.isEmpty()) "Select Date of Birth" else "Date of Birth: ${state.dob}",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Start
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Education Information Section
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = colorScheme.surfaceVariant.copy(alpha = 0.7f)
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 2.dp
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Education Information",
                            style = MaterialTheme.typography.titleMedium,
                            color = colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.Bold
                        )

                        // College Name Input
                        AutocompleteTextField(
                            modifier = Modifier.fillMaxWidth(),
                            onItemSelected = { viewModel.addCollegeName(it) },
//                            label = "College/University",
//                            leadingIcon = {
//                                Icon(
//                                    Icons.Default.School,
//                                    contentDescription = "College"
//                                )
//                            }
                        )

                        // Degree Name Input
                        EnhancedInputField(
                            placeholder = "Your Degree",
                            onValueChange = { viewModel.addDegreeName(it) },
                            input = state.degreeName,
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Face,
                                    contentDescription = "Degree"
                                )
                            }
                        )

                        // Role Dropdown Menu
                        ExposedDropdownMenuBox(
                            expanded = state.roleMenuExpanded,
                            onExpandedChange = { viewModel.toggleRoleMenu() }
                        ) {
                            OutlinedTextField(
                                value = state.role.ifEmpty { "" },
                                onValueChange = {},
                                readOnly = true,
                                label = { Text("Select Role") },
                                leadingIcon = {
                                    Icon(
                                        Icons.Default.AccountBox,
                                        contentDescription = "Role"
                                    )
                                },
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = state.roleMenuExpanded)
                                },
                                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .menuAnchor()
                            )

                            ExposedDropdownMenu(
                                expanded = state.roleMenuExpanded,
                                onDismissRequest = { viewModel.toggleRoleMenu() }
                            ) {
                                listOf("Student", "Alumni").forEach { role ->
                                    DropdownMenuItem(
                                        text = { Text(text = role) },
                                        onClick = {
                                            viewModel.addRole(role)
                                            viewModel.toggleRoleMenu()
                                        }
                                    )
                                }
                            }
                        }

                        // Graduation Year Inputs
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedButton(
                                onClick = { fromGraduationYearPicker.show() },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = colorScheme.primary
                                )
                            ) {
                                Text(
                                    text = if (state.fromGraduation.isEmpty()) "Start Year" else state.fromGraduation
                                )
                            }

                            OutlinedButton(
                                onClick = { toGraduationYearPicker.show() },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = colorScheme.primary
                                )
                            ) {
                                Text(
                                    text = if (state.toGraduation.isEmpty()) "End Year" else state.toGraduation
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(80.dp)) // Space for FAB
            }

            // Success message
            AnimatedVisibility(
                visible = showSuccessMessage,
                enter = fadeIn(animationSpec = tween(400)),
                exit = fadeOut(animationSpec = tween(400)),
                modifier = Modifier.align(Alignment.Center)
            ) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = colorScheme.primaryContainer
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            Icons.Default.CheckCircle,
                            contentDescription = "Success",
                            tint = colorScheme.primary,
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Profile Created Successfully!",
                            style = MaterialTheme.typography.titleLarge,
                            color = colorScheme.onPrimaryContainer
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            onClick = {
                                showSuccessMessage = false
                                navigateToHomeScreen()},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorScheme.primary
                            )
                        ) {
                            Text("Continue")
                        }
                    }
                }
            }
        }
    }
}

// Add these new component files to your project
@Composable
fun EnhancedInputField(
    placeholder: String,
    onValueChange: (String) -> Unit,
    input: String,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        value = input,
        onValueChange = onValueChange,
        label = { Text(placeholder) },
        leadingIcon = leadingIcon,
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            focusedLabelColor = MaterialTheme.colorScheme.primary
        ),
        singleLine = true
    )
}


// Helper function for showing snackbars
private suspend fun showSnackbar(snackbarHostState: SnackbarHostState, message: String) {
    snackbarHostState.showSnackbar(
        message = message,
        duration = SnackbarDuration.Short
    )
}