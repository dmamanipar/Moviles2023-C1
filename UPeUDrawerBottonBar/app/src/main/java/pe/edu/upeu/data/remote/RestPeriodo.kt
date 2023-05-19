package pe.edu.upeu.data.remote

import pe.edu.upeu.modelo.Actividad
import pe.edu.upeu.modelo.MsgModelPerResp
import pe.edu.upeu.modelo.MsgModelResp
import pe.edu.upeu.modelo.Periodo
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

interface RestPeriodo {
    @GET("/api/periodo")
    suspend fun reportarPeriodo(@Header("Authorization") token:String):
            Response<MsgModelPerResp>
    @GET("/api/periodo/{id}")
    suspend fun getPeriodoId(@Header("Authorization") token:String,
                               @Query("id") id:Int): Response<MsgModelPerResp>
    @DELETE("/api/periodo/{id}")
    suspend fun deletePeriodo(@Header("Authorization") token:String,
                                @Path("id") id:Int): Response<MsgModelPerResp>
    @PATCH("/api/periodo/{id}")
    suspend fun actualizarPeriodo(@Header("Authorization")
                                    token:String, @Path("id") id:Int, @Body periodo: Periodo):
            Response<MsgModelPerResp>
    @POST("/api/periodo")
    suspend fun insertarPeriodo(@Header("Authorization") token:String,
                                  @Body Periodo: Periodo): Response<MsgModelPerResp>
    @POST("/api/auth/login")
    suspend fun login(@Body user: User):Response<UserResponse>
}