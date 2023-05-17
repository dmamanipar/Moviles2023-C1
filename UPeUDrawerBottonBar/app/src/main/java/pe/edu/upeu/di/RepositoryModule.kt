package pe.edu.upeu.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.edu.upeu.repository.ActividadRepository
import pe.edu.upeu.repository.ActividadRepositoryImp
import pe.edu.upeu.repository.AsistenciaRepository
import pe.edu.upeu.repository.AsistenciaRepositoryImp
import pe.edu.upeu.repository.UserRepository
import pe.edu.upeu.repository.UserRepositoryImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract  fun actividadRepository(actRepos:ActividadRepositoryImp): ActividadRepository

    @Binds
    @Singleton
    abstract  fun userRepository(userRepos:UserRepositoryImp): UserRepository

    @Binds
    @Singleton
    abstract fun asistenciaRepository(asisRepos:AsistenciaRepositoryImp): AsistenciaRepository

}