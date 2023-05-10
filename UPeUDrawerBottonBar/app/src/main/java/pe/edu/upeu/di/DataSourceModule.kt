package pe.edu.upeu.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pe.edu.upeu.data.local.DbDataSource
import pe.edu.upeu.data.local.dao.ActividadDao
import pe.edu.upeu.data.local.dao.AsisteciapaDao
import pe.edu.upeu.data.remote.RestActividad
import pe.edu.upeu.data.remote.RestAsisteciapa
import pe.edu.upeu.utils.TokenUtils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Singleton
    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl()= TokenUtils.API_URL
    //"http://192.168.1.124:8001/"
    @Singleton
    @Provides
    fun provideRetrofit(@Named("BaseUrl") baseUrl:String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl).build()
    }

    @Singleton
    @Provides
    fun restActividad(retrofit: Retrofit):RestActividad{
        return retrofit.create(RestActividad::class.java)
    }
    fun restAsisteciapa(retrofit: Retrofit):RestAsisteciapa{
        return retrofit.create(RestAsisteciapa::class.java)
    }

    @Singleton
    @Provides
    fun dbDataSource(@ApplicationContext context: Context):DbDataSource{
        return Room.databaseBuilder(context, DbDataSource::class.java,
            "asistencia_db")
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun actividadDao(db:DbDataSource):ActividadDao{
        return db.actividadDao();
    }
    fun asisteciaDao(db:DbDataSource):AsisteciapaDao{
        return db.asisteciapaDao();
    }
}
