package com.liceo.liceochat.data.repository

import android.util.Log
import com.liceo.liceochat.core.AppResult
import com.liceo.liceochat.data.network.ChatApiService
import com.liceo.liceochat.data.network.dto.NewMessageDto
import com.liceo.liceochat.data.network.dto.toDomain
import com.liceo.liceochat.domain.ChatRepository
import java.io.IOException
import java.net.UnknownHostException
import java.net.SocketTimeoutException
import com.liceo.liceochat.domain.Message
import retrofit2.HttpException

class ChatRepositoryImpl(
    private val api: ChatApiService
) : ChatRepository {

    override suspend fun getMessages(): AppResult<List<Message>> {
        return safeCall {
            api.getMessages().toDomain()
        }
    }

    override suspend fun sendMessage(
        sender: String,
        text: String
    ): AppResult<Unit> {
        return safeCall {
            val dto = NewMessageDto(
                sender = sender,
                text = text,
                createdAt = System.currentTimeMillis()
            )

            api.sendMessage(dto)
            Unit
        }
    }

    private suspend fun <T> safeCall(
        block: suspend () -> T
    ): AppResult<T> {
        return try {
            AppResult.Success(block())
        } catch (e: UnknownHostException) {
            Log.e("ChatRepository", "UnknownHostException", e)
            AppResult.Failure.NoInternet
        } catch (e: SocketTimeoutException) {
            Log.e("ChatRepository", "SocketTimeoutException", e)
            AppResult.Failure.Timeout
        } catch (e: HttpException) {
            Log.e("ChatRepository", "HttpException: ${e.code()}", e)
            val errorBody = e.response()?.errorBody()?.string()
            if (e.code() == 400 && errorBody?.contains("Max number of elements") == true) {
                AppResult.Failure.Unknown("Server storage full (100 messages limit reached).")
            } else {
                AppResult.Failure.Unknown("Server error: ${e.code()}")
            }
        } catch (e: IOException) {
            Log.e("ChatRepository", "IOException", e)
            AppResult.Failure.NoInternet
        } catch (e: Exception) {
            Log.e("ChatRepository", "Unknown Exception", e)
            AppResult.Failure.Unknown(e.message)
        }
    }
}