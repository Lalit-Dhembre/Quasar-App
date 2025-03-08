package com.cosmicstruck.linkspehere.mentorsFeature.domain.model

data class MentorsModel(
    val uuid: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val collegeName: String? = "",
    val skills : String = "",
    val profile: String = ""
)