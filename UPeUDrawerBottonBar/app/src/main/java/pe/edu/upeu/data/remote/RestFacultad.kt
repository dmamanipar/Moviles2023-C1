package pe.edu.upeu.data.remote

import pe.edu.upeu.modelo.Actividad
import pe.edu.upeu.modelo.Facultad
import pe.edu.upeu.modelo.MsgModelResp
import pe.edu.upeu.modelo.MsgModelResp2
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

interface RestFacultad {
    @GET("/api/act")
    suspend fun reportarFacultad(@Header("Authorization") token:String):
            Response<MsgModelResp2>
    @GET("/api/facu/{id}")
    suspend fun getFacultadId(@Header("Authorization") token:String,
                               @Query("id") id:Int): Response<MsgModelResp2>
    @DELETE("/api/facu/{id}")
    suspend fun deleteFacultad(@Header("Authorization") token:String,
                                @Path("id") id:Int): Response<MsgModelResp2>
    @PATCH("/api/facu/{id}")
    suspend fun actualizarFacultad(@Header("Authorization")
                                    token:String, @Path("id") id:Int, @Body facultad: Facultad):
            Response<MsgModelResp2>
    @POST("/api/facu")
    suspend fun insertarFacultad(@Header("Authorization") token:String,
                                  @Body facultad: Facultad): Response<MsgModelResp2>
    @POST("/api/auth/login")
    suspend fun login(@Body user: User):Response<UserResponse>
}