package pe.edu.upeu.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.edu.upeu.repository.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract  fun actividadRepository(actRepos:ActividadRepositoryImp): ActividadRepository
    @Binds
    @Singleton
    abstract fun asisteciapaRepository(actRepos:AsisteciapaRepositoryImp): AsisteciapaRepository

    @Binds
    @Singleton
    abstract  fun userRepository(userRepos:UserRepositoryImp): UserRepository

}