package com.cosmicstruck.linkspehere.mentorsFeature.presentation

import com.cosmicstruck.linkspehere.mentorsFeature.domain.model.MentorsModel

data class MentorsScreenState(
    val mentorsList: List<MentorsModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)