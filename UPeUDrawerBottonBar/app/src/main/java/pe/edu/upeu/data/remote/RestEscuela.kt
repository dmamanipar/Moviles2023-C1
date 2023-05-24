package pe.edu.upeu.data.remote

import pe.edu.upeu.modelo.Escuela
import pe.edu.upeu.modelo.MsgModelRespEs
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

interface RestEscuela {
    @GET("/api/escuela")
    suspend fun reportarEscuela(@Header("Authorization") token:String):
            Response<MsgModelRespEs>
    @GET("/api/escuela/{id}")
    suspend fun getEscuelaId(@Header("Authorization") token:String,
                               @Query("id") id:Int): Response<MsgModelRespEs>
    @DELETE("/api/escuela/{id}")
    suspend fun deleteEscuela(@Header("Authorization") token:String,
                                @Path("id") id:Int): Response<MsgModelRespEs>
    @PATCH("/api/escuela/{id}")
    suspend fun actualizarEscuela(@Header("Authorization")
                                    token:String, @Path("id") id:Int, @Body escuela: Escuela):
            Response<MsgModelRespEs>
    @POST("/api/escuela")
    suspend fun insertarEscuela(@Header("Authorization") token:String,
                                  @Body escuela: Escuela): Response<MsgModelRespEs>
    @POST("/api/auth/login")
    suspend fun login(@Body user: User):Response<UserResponse>
}