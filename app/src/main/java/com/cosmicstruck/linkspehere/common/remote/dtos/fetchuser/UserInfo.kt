package com.cosmicstruck.linkspehere.common.remote.dtos.fetchuser

data class UserInfo(
    val age: Int,
    val bio: String,
    val degree: String,
    val first_name: String,
    val graduation_year: Int,
    val institution_id: String,
    val last_name: String,
    val linkedin_url: String,
    val location: String,
    val profile_photo: String,
    val role: String,
    val skills: List<String>,
    val uuid: String
)