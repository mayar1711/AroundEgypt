package com.example.aroundegypt.data.repo

import com.example.aroundegypt.data.local.LocalDataSource
import com.example.aroundegypt.data.local.LocalDataSourceImp
import com.example.aroundegypt.data.remote.datasource.RemoteDataSource
import com.example.aroundegypt.data.remote.datasource.RemoteDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
    @Binds
    abstract fun bindRepository(repositoryImp: RepositoryImp): Repository

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImp: RemoteDataSourceImp): RemoteDataSource
/*

    @Binds
    abstract fun bindLocalDataSource(localDataSourceImp: LocalDataSourceImp): LocalDataSource
*/

}