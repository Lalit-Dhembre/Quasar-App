package com.cosmicstruck.linkspehere.mentorsFeature.domain.usecase

import com.cosmicstruck.linkspehere.common.remote.dtos.mentorMatching.toMentorModel
import com.cosmicstruck.linkspehere.common.remote.service.QuasarService
import com.cosmicstruck.linkspehere.common.utils.Resource
import com.cosmicstruck.linkspehere.mentorsFeature.domain.model.MentorsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FetchMentors(
    private val service: QuasarService
) {
    operator fun invoke(
        uuid: String): Flow<Resource<List<MentorsModel>>> = flow {
        try {
            emit(Resource.Loading())
            val mentorsList = service.getMentors(uuid)
            val toPass = mentorsList.matches.map { it.toMentorModel() }
            emit(Resource.Success(data = toPass))
        }catch (e: Exception){
            emit(Resource.Failure(message = "${e.message.toString()}"))
        }
    }
}