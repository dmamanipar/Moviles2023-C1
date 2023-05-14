package pe.edu.upeu.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.edu.upeu.repository.ActividadRepository
import pe.edu.upeu.repository.PersonaRepository
import pe.edu.upeu.repository.ActividadRepositoryImp
import pe.edu.upeu.repository.PersonaRepositoryImp
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
    abstract  fun personaRepository(persRepos:PersonaRepositoryImp): PersonaRepository

    @Binds
    @Singleton
    abstract  fun userRepository(userRepos:UserRepositoryImp): UserRepository

}