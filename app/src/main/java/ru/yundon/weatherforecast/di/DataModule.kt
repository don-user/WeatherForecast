package ru.yundon.weatherforecast.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.yundon.weatherforecast.data.CityWeatherMapper
import ru.yundon.weatherforecast.data.database.CitiesWeatherDatabase
import ru.yundon.weatherforecast.data.datarepository.CitiesWeatherLocalStorage
import ru.yundon.weatherforecast.data.datarepository.CitiesWeatherRemoteStorage
import ru.yundon.weatherforecast.data.datarepository.CitiesWeatherRepositoryImpl
import ru.yundon.weatherforecast.domain.CitiesWeatherRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : CitiesWeatherDatabase{
        return CitiesWeatherDatabase.buildDatabase(context)
    }


    @Provides
    @Singleton
    fun provideLocalStorage(database: CitiesWeatherDatabase) : CitiesWeatherLocalStorage{
        return CitiesWeatherLocalStorage(database)
    }

    @Provides
    @Singleton
    fun provideRemoveStorage() : CitiesWeatherRemoteStorage {
        return CitiesWeatherRemoteStorage()
    }

    @Provides
    @Singleton
    fun provideMapper() : CityWeatherMapper{
        return CityWeatherMapper()
    }

    @Provides
    @Singleton
    fun provideCitiesWeatherRepository(
        localStorage: CitiesWeatherLocalStorage,
        remoteStorage: CitiesWeatherRemoteStorage,
        mapper: CityWeatherMapper
    ): CitiesWeatherRepository {
        return CitiesWeatherRepositoryImpl(
            localStorage = localStorage,
            removeStorage = remoteStorage,
            mapper = mapper
        )
    }
}