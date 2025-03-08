package com.cosmicstruck.linkspehere.profileBuilding.presentation

import android.net.Uri


data class ProfileBuildingScreenState(
    val profilePhotoUri: Uri? = null,
    val firstName: String = "",
    val lastName: String = "",
    val collegeName: String = "",
    val degreeName: String = "",
    val dob: String = "",
    val fromGraduation: String = "",
    val toGraduation: String = "",
    val role: String = "",
    val roleMenuExpanded: Boolean = false,
    val showValidationErrors: Boolean = false,
    val isSubmitting: Boolean = false,
    val isSubmitSuccessful: Boolean = false
)