package pe.edu.upeu.repository

import pe.edu.upeu.data.remote.RestActividad
import pe.edu.upeu.data.remote.RestMatricula
import pe.edu.upeu.modelo.User
import pe.edu.upeu.modelo.UserResponse
import retrofit2.Response
import javax.inject.Inject

interface UserRepository {
    suspend fun loginUser(user:User): Response<UserResponse>
}

class UserRepositoryImp @Inject constructor(
    private val restActividad: RestActividad,
    private val restMatricula: RestMatricula

):UserRepository {
    override suspend fun loginUser(user: User): Response<UserResponse> {
        return restActividad.login(user)
        return restMatricula.login(user)

    }
}
