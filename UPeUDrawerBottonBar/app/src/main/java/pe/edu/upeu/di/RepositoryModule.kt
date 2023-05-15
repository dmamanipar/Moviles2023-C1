package pe.edu.upeu.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.edu.upeu.repository.ActividadRepository
import pe.edu.upeu.repository.ActividadRepositoryImp
import pe.edu.upeu.repository.EscuelaRepository
import pe.edu.upeu.repository.EscuelaRepositoryImp
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
    abstract  fun escuelaRepository(actRepos:EscuelaRepositoryImp): EscuelaRepository

    @Binds
    @Singleton
    abstract  fun userRepository(userRepos:UserRepositoryImp): UserRepository

}