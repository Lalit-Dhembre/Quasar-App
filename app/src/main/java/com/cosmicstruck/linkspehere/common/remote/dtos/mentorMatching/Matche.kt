package com.cosmicstruck.linkspehere.common.remote.dtos.mentorMatching

import com.cosmicstruck.linkspehere.mentorsFeature.domain.model.MentorsModel

data class Matche(
    val age: Int,
    val bio: String,
    val first_name: String,
    val graduation_year: Int,
    val institution_id: String,
    val last_name: String,
    val linkedin_url: String,
    val location: String,
    val profile_photo: String,
    val skills: String,
    val uuid: String
)

fun Matche.toMentorModel() : MentorsModel{
    return MentorsModel(
        uuid = uuid,
        firstName = first_name,
        lastName = last_name,
        collegeName = institution_id,
        skills = skills,
        profile = profile_photo
    )
}