package pe.edu.upeu.data.remote

import pe.edu.upeu.modelo.*
import retrofit2.Response
import retrofit2.http.*

interface RestEvento {
    @GET("/api/evento")
    suspend fun reportarEvento(@Header("Authorization") token:String):
            Response<MsgModelpRes>
    @GET("/api/evento/{id}")
    suspend fun getEventoId(@Header("Authorization") token:String,
                             @Query("id") id:Int): Response<MsgModelpRes>
    @DELETE("/api/evento/{id}")
    suspend fun deleteEvento(@Header("Authorization") token:String,
                              @Path("id") id:Int): Response<MsgModelpRes>
    @PATCH("/api/evento/{id}")
    suspend fun actualizarEvento(@Header("Authorization")
                                  token:String, @Path("id") id:Int, @Body evento: Evento
    ):
            Response<MsgModelpRes>
    @POST("/api/evento")
    suspend fun insertarEvento(@Header("Authorization") token:String,
                                @Body evento: Evento
    ): Response<MsgModelpRes>
    @POST("/api/auth/login")
    suspend fun login(@Body user: User): Response<UserResponse>
}