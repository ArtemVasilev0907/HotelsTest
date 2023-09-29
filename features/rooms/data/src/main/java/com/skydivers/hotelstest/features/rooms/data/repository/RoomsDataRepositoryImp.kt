package com.skydivers.hotelstest.features.rooms.data.repository




import com.skydivers.hotelstest.features.rooms.domain.model.RoomsModelDomain
import com.skydivers.hotelstest.core.common.UiState
import com.skydivers.hotelstest.features.rooms.data.entities.RoomsEntity
import com.skydivers.hotelstest.features.rooms.data.service.RoomsService
import com.skydivers.hotelstest.features.rooms.domain.repository.RoomsDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response


internal class RoomsDataRepositoryImp(
    private val service: RoomsService
) :
    RoomsDataRepository {
    override suspend fun fetchRoomsData(): Flow<UiState<RoomsModelDomain>> =
        flow {

            emit(fetchRoomsDataFromServer().suspendMap {
                it.mapToDomain()
            })


        }.flowOn(Dispatchers.IO)
            .catch {
                emit(UiState.Error(Exception(it)))
            }


    private suspend fun fetchRoomsDataFromServer(): UiState<RoomsEntity> {
        return setState(response = service.getData())
    }


    private fun setState(
        response: Response<RoomsEntity>
    ): UiState<RoomsEntity> {
        var uiState: UiState<RoomsEntity> = UiState.Loading
        try {

            if (response.code() == 200) {
                uiState = UiState.Success(response.body()!!)
            }
        } catch (e: Exception) {
            UiState.Error( e )

        }

        return uiState
    }


}

