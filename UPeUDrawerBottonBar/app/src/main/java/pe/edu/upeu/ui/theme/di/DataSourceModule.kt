package pe.edu.upeu.ui.theme.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import pe.edu.upeu.data.local.DbDataSource
import pe.edu.upeu.data.local.DbDataSourceEscuela
import pe.edu.upeu.data.local.dao.ActividadDao
import pe.edu.upeu.data.local.dao.EscuelaDao
import pe.edu.upeu.data.remote.RestActividad
import pe.edu.upeu.data.remote.RestEscuela
import pe.edu.upeu.utils.TokenUtils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    var retrofit:Retrofit?=null
    @Singleton
    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl()= TokenUtils.API_URL
    //"http://192.168.0.106:8000/"
    @Singleton
    @Provides
    fun provideRetrofit(@Named("BaseUrl") baseUrl:String): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        if(retrofit==null){
            retrofit= Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(baseUrl).build()
        }
        return  retrofit!!
    }

    @Singleton
    @Provides
    fun restActividad(retrofit: Retrofit):RestActividad{
        return retrofit.create(RestActividad::class.java)
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

    //Escuela//

    @Singleton
    @Provides
    fun restEscuela(retrofit: Retrofit):RestEscuela{
        return retrofit.create(RestEscuela::class.java)
    }

    @Singleton
    @Provides
    fun dbDataSourceEscuela(@ApplicationContext context: Context):DbDataSourceEscuela{
        return Room.databaseBuilder(context, DbDataSourceEscuela::class.java,
            "escuela_db")
            .fallbackToDestructiveMigration().build()
    }
    @Singleton
    @Provides
    fun escuelaDao(db:DbDataSourceEscuela):EscuelaDao{
        return db.escuelaDao();
    }
}