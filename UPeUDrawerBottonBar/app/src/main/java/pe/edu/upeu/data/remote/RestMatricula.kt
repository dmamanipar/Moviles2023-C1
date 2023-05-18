package pe.edu.upeu.data.remote

import pe.edu.upeu.modelo.Matricula
import pe.edu.upeu.modelo.MsgModelRespMat
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

interface RestMatricula {
    @GET("/api/matriculas")
    suspend fun reportarMatricula(@Header("Authorization") token:String):
            Response<MsgModelRespMat>
    @GET("/api/matriculas/{id}")
    suspend fun getMatriculaId(@Header("Authorization") token:String,
                               @Query("id") id:Int): Response<MsgModelRespMat>
    @DELETE("/api/matriculas/{id}")
    suspend fun deleteMatricula(@Header("Authorization") token:String,
                                @Path("id") id:Int): Response<MsgModelRespMat>
    @PATCH("/api/matriculas/{id}")
    suspend fun actualizarMatricula(@Header("Authorization")
                                    token:String, @Path("id") id:Int, @Body matricula: Matricula
    ):
            Response<MsgModelRespMat>
    @POST("/api/matriculas")
    suspend fun insertarMatricula(@Header("Authorization") token:String,
                                  @Body matricula: Matricula
    ): Response<MsgModelRespMat>
    @POST("/api/auth/login")
    suspend fun login(@Body user: User): Response<UserResponse>
}