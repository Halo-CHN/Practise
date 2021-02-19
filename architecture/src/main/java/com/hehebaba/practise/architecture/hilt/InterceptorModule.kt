package com.hehebaba.practise.architecture.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OtherInterceptor

@Module
@InstallIn(ApplicationComponent::class)
object InterceptorModule {

    @AuthInterceptor
    @Provides
    fun provideAuthInterceptor(): AuthInterceptorImp {
        return AuthInterceptorImp()
    }

    @OtherInterceptor
    @Provides
    fun provideOtherInterceptor(): OtherInterceptorImp {
        return OtherInterceptorImp()
    }

    class AuthInterceptorImp : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            return chain.proceed(chain.request())
        }
    }

    class OtherInterceptorImp : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            return chain.proceed(chain.request())
        }
    }
}

