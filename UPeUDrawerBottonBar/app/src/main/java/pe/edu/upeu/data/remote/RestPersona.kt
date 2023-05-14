package pe.edu.upeu.data.remote

import pe.edu.upeu.modelo.*
import retrofit2.Response
import retrofit2.http.*

interface RestPersona {
    @GET("/api/persona")
    suspend fun reportarPersona(@Header("Authorization") token:String):
            Response<MsgModelpRes>
    @GET("/api/persona/{id}")
    suspend fun getPersonaId(@Header("Authorization") token:String,
                               @Query("id") id:Int): Response<MsgModelpRes>
    @DELETE("/api/persona/{id}")
    suspend fun deletePersona(@Header("Authorization") token:String,
                                @Path("id") id:Int): Response<MsgModelpRes>
    @PATCH("/api/persona/{id}")
    suspend fun actualizarPersona(@Header("Authorization")
                                    token:String, @Path("id") id:Int, @Body persona: Persona
    ):
            Response<MsgModelpRes>
    @POST("/api/persona")
    suspend fun insertarPersona(@Header("Authorization") token:String,
                                  @Body persona: Persona
    ): Response<MsgModelpRes>
    @POST("/api/auth/login")
    suspend fun login(@Body user: User): Response<UserResponse>
}