package com.example.myapplication.di



import android.content.Context
import androidx.room.Room
import com.example.myapplication.datastore.ProductDataStore
import com.example.myapplication.datastore.ProductDataStoreImpl
import com.example.myapplication.db.AppDataBase
import com.example.myapplication.db.DbSource
import com.example.myapplication.db.ProductDb
import com.example.myapplication.network.HttpClientBuilderFactory
import com.example.myapplication.network.Services
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesService(httpClient: HttpClientBuilderFactory): Services = Services.createService(httpClient)


    @Provides
    @Singleton
    fun provideHttpBuilderFactory() : HttpClientBuilderFactory = HttpClientBuilderFactory()

    @Provides
    fun provideProductRoom(@ApplicationContext context: Context) : AppDataBase = DbSource(context).createDB()

    @Provides
    fun provideProductDataBase(appDataBase: AppDataBase) : ProductDb = ProductDb(appDataBase)

    @Provides
    fun provideProductDataStore(services: Services, productDb: ProductDb) : ProductDataStore = ProductDataStoreImpl(services,productDb)

}