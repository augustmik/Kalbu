package lt.autismus.dagger

import dagger.Module
import dagger.Provides
import lt.autismus.repository.CardsRepo
import lt.autismus.roomDb.RoomDBService
import javax.inject.Singleton

@Module
class RepoModule {
    @Provides
    @Singleton
    fun provideRoomDBService(roomService : RoomDBService): CardsRepo = CardsRepo(roomService)
}