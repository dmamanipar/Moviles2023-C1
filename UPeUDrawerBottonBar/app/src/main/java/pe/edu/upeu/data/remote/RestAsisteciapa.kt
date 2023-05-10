package pe.edu.upeu.data.remote

import pe.edu.upeu.modelo.Asisteciapa
import pe.edu.upeu.modelo.MsgModelRespasis
import pe.edu.upeu.modelo.User
import pe.edu.upeu.modelo.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RestAsisteciapa {
    @GET("/api/asispa")
    suspend fun reportarAsisteciapa(
        @Header("Authorization") token:String):Response<MsgModelRespasis>

    @GET("/api/asispa/{id}")
    suspend fun getAsistecipaId(
        @Header("Authorization") token:String,
        @Query("id") id:Int): Response<MsgModelRespasis>

    @DELETE("/api/asispa/{id}")
    suspend fun deleteAsisteciapa(
        @Header("Authorization") token:String,
        @Path("id") id:Int): Response<MsgModelRespasis>

    @PATCH("/api/asispa/{id}")
    suspend fun actualizarAsisteciapa(
        @Header("Authorization") token:String,
        @Path("id") id:Int, @Body asisteciapa: Asisteciapa):Response<MsgModelRespasis>

    @POST("/api/asispa")
    suspend fun insertarAsisteciapa(
        @Header("Authorization") token:String,
        @Body asisteciapa: Asisteciapa): Response<MsgModelRespasis>

    @POST("/api/auth/login")
    suspend fun login(@Body user: User):UserResponse
}