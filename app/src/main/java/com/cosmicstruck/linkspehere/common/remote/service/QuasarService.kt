package com.cosmicstruck.linkspehere.common.remote.service

import com.cosmicstruck.linkspehere.common.remote.dtos.fetchuser.FetchUserDTO
import com.cosmicstruck.linkspehere.common.remote.dtos.mentorMatching.MentorMatchingDTO
import com.cosmicstruck.linkspehere.common.remote.dtos.smartConnect.SmartConnectDTO
import retrofit2.http.GET
import retrofit2.http.Query


interface QuasarService {
    @GET("mentorMatching/")
    suspend fun getMentors(
        @Query ("uuid") uuid: String
    ): MentorMatchingDTO

    @GET("smartConnect/")
    suspend fun smartConnect(
        @Query("uuid") uuid : String,
        @Query("user_type") userType: String
    ): SmartConnectDTO

    @GET("genrateToken1/")
    suspend fun generateToken(
        @Query("uuid") uuid : String
    ): String

    @GET("fquery/fetchUser")
    suspend fun fetchUser(
        @Query("uuid") uuid : String
    ): FetchUserDTO
}