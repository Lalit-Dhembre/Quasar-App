package com.cosmicstruck.linkspehere.profileBuilding.presentation

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileBuildingViewModel @Inject constructor() : ViewModel() {

    private val _profileBuildingScreenState = mutableStateOf(ProfileBuildingScreenState())
    val profileBuildingScreenState: State<ProfileBuildingScreenState> = _profileBuildingScreenState

    // Form field manipulation functions
    fun addProfilePhoto(image: Uri?) {
        _profileBuildingScreenState.value = _profileBuildingScreenState.value.copy(
            profilePhotoUri = image
        )
    }

    fun addFirstName(fname: String) {
        _profileBuildingScreenState.value = _profileBuildingScreenState.value.copy(
            firstName = fname
        )
    }

    fun addLastName(lname: String) {
        _profileBuildingScreenState.value = _profileBuildingScreenState.value.copy(
            lastName = lname
        )
    }

    fun addCollegeName(college: String) {
        _profileBuildingScreenState.value = _profileBuildingScreenState.value.copy(
            collegeName = college
        )
    }

    fun addDegreeName(degree: String) {
        _profileBuildingScreenState.value = _profileBuildingScreenState.value.copy(
            degreeName = degree
        )
    }

    fun addDOB(dob: String) {
        _profileBuildingScreenState.value = _profileBuildingScreenState.value.copy(
            dob = dob
        )
    }

    fun addFromGraduation(from: String) {
        _profileBuildingScreenState.value = _profileBuildingScreenState.value.copy(
            fromGraduation = from
        )
    }

    fun addToGraduation(to: String) {
        _profileBuildingScreenState.value = _profileBuildingScreenState.value.copy(
            toGraduation = to
        )
    }

    fun addRole(role: String) {
        _profileBuildingScreenState.value = _profileBuildingScreenState.value.copy(
            role = role
        )
    }

    // Dropdown menu state toggle
    fun toggleRoleMenu() {
        _profileBuildingScreenState.value = _profileBuildingScreenState.value.copy(
            roleMenuExpanded = !_profileBuildingScreenState.value.roleMenuExpanded
        )
    }

    // Form validation function
    fun validateFields(): Boolean {
        val isValid = _profileBuildingScreenState.value.run {
            firstName.isNotEmpty() &&
                    lastName.isNotEmpty() &&
                    collegeName.isNotEmpty() &&
                    degreeName.isNotEmpty() &&
                    dob.isNotEmpty() &&
                    fromGraduation.isNotEmpty() &&
                    toGraduation.isNotEmpty() &&
                    role.isNotEmpty()
        }

        // If validation fails, set error state to show visual indicators
        if (!isValid) {
            _profileBuildingScreenState.value = _profileBuildingScreenState.value.copy(
                showValidationErrors = true
            )

            // Reset error state after a delay
            viewModelScope.launch {
                delay(3000)
                _profileBuildingScreenState.value = _profileBuildingScreenState.value.copy(
                    showValidationErrors = false
                )
            }
        }

        return isValid
    }

    // Function to submit the profile data
    fun submitProfile(onSuccess: () -> Unit) {
        if (validateFields()) {
            // In a real app, you would send data to repository or API here
            viewModelScope.launch {
                // Simulate network delay
                _profileBuildingScreenState.value = _profileBuildingScreenState.value.copy(
                    isSubmitting = true
                )

                delay(1500) // Simulate API call

                _profileBuildingScreenState.value = _profileBuildingScreenState.value.copy(
                    isSubmitting = false,
                    isSubmitSuccessful = true
                )

                onSuccess()

                // Reset success state after a delay
                delay(5000)
                _profileBuildingScreenState.value = _profileBuildingScreenState.value.copy(
                    isSubmitSuccessful = false
                )
            }
        }
    }

    // Reset all form fields
    fun resetForm() {
        _profileBuildingScreenState.value = ProfileBuildingScreenState()
    }
}

// Updated state class
