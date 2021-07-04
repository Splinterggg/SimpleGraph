package com.app.simplegraph

import com.app.simplegraph.data.BitcoinEndpoint
import com.app.simplegraph.data.DataSource
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    private val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
    private val okHttpClient: OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BitcoinEndpoint.SERVER)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideDataSource(bitcoinEndpoint: BitcoinEndpoint) =
        DataSource(bitcoinEndpoint)


    @Provides
    @Singleton
    fun provideClientsApi(retrofit: Retrofit): BitcoinEndpoint =
        retrofit.create(BitcoinEndpoint::class.java)
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope