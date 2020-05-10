package lt.kalbu.dagger

import dagger.Module
import dagger.Provides
import lt.kalbu.repository.CardsRepo
import lt.kalbu.roomDb.RoomDBService
import lt.kalbu.story.CardSelection
import javax.inject.Singleton

@Module
class RepoModule {
    @Provides
    @Singleton
    fun provideRoomDBService(roomService : RoomDBService): CardsRepo = CardsRepo(roomService)

    @Provides
    @Singleton
    fun provideCardSelector(): CardSelection = CardSelection()
}