package com.example.pollcreator.onlineStorage

import android.content.Context
import android.util.Log
import androidx.compose.runtime.CompositionLocalContext
import com.example.pollcreator.dataclass.Event
import com.example.pollcreator.dataclass.Poll
import com.example.pollcreator.repository.metaMaskRepository

import io.metamask.androidsdk.*
import kotlin.coroutines.coroutineContext


class metaMaskDataModel(localContext: Context) : metaMaskRepository {

    var  localContext : Context = localContext


    // to create the dapp and initialise it

    val ethereum = Ethereum(context = localContext)
    val dapp = Dapp("poll", "https://eth-sepolia.g.alchemy.com/v2/zWgAYQQLQFteREbHKp7F5RakZ-a_8gXA")

    fun connectDapp(){
        ethereum.connect(dapp){
            result ->
            if(result is RequestError){
                Log.d("error" , result.message)
            }else{
                Log.d("success" , result.toString())
            }
        }
    }




    fun getBalance(){


        var balance: String? = null

// Create parameters.
        val params: List<String> = listOf(
            ethereum.selectedAddress,
            // "latest", "earliest", or "pending" (optional)
            "latest"
        )

// Create request.
        val getBalanceRequest = EthereumRequest(
            method = EthereumMethod.ETH_GET_BALANCE.value,
            params = params
        )

// Make request.
        ethereum.sendRequest(getBalanceRequest) { result ->
            if (result is RequestError) {
                // Handle error.
                Log.d("error" , result.message)
            } else {
                balance = result.toString()
            }
        }
    }





















    override suspend fun createPoll(poll: Poll): Event {
        TODO("Not yet implemented")
    }

    override suspend fun castVote(pollid: Double, aadharNo: Long): Event {
        TODO("Not yet implemented")
    }

    override suspend fun getAllPollCreatedByAdmin(aadharNo: Long): List<Poll> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllUpcomingPoll(age: Int): List<Poll> {
        TODO("Not yet implemented")
    }

    override suspend fun getPreviousPoll(): List<Poll> {
        TODO("Not yet implemented")
    }

    override suspend fun becomeCandidateOfPoll(pollid: Double, aadharNoOfAdmin: Long): Event {
        TODO("Not yet implemented")
    }
}