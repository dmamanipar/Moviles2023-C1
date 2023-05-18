package pe.edu.upeu.data.remote

import pe.edu.upeu.modelo.Asistencia
import pe.edu.upeu.modelo.MsgModelRespAsis
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

interface RestAsistencia {
    @GET("/api/asistencia")
    suspend fun reportarAsistencia(@Header("Authorization") token:String):
            Response<MsgModelRespAsis>
    @GET("/api/asistencia/{id}")
    suspend fun getAsistenciaId(@Header("Authorization") token:String,
                                @Query("id") id:Int): Response<MsgModelRespAsis>
    @DELETE("/api/asistencia/{id}")
    suspend fun deleteAsistencia(@Header("Authorization") token:String,
                                 @Path("id") id:Int): Response<MsgModelRespAsis>
    @PATCH("/api/asistencia/{id}")
    suspend fun actualizarAsistencia(@Header("Authorization")
                                     token:String, @Path("id") id:Int, @Body asistencia: Asistencia):
            Response<MsgModelRespAsis>
    @POST("/api/asistencia")
    suspend fun insertarAsistencia(@Header("Authorization") token:String,
                                   @Body asistencia: Asistencia): Response<MsgModelRespAsis>
    @POST("/api/auth/login")
    suspend fun login(@Body user: User):Response<UserResponse>
}