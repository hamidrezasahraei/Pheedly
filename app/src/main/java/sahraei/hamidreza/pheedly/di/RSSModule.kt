package sahraei.hamidreza.pheedly.di

import android.content.Context
import com.prof.rssparser.Parser
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object RSSModule {

    @Provides
    @Reusable
    fun provideRSSParser(
        @ApplicationContext context: Context
    ): Parser {
        return Parser.Builder()
            .context(context)
            .cacheExpirationMillis(TimeUnit.HOURS.toMillis(2))
            .build()
    }
}