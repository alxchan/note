package com.example.login.card.data

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.FileWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class CardViewModel(application: Application) : AndroidViewModel(application){
    private val _cardList:  MutableLiveData<MutableList<cardInfo>> = MutableLiveData()
    val cardList : LiveData<MutableList<cardInfo>> get() = _cardList
    private val _workspaceList:  MutableLiveData<MutableList<WorkspaceInfo>> = MutableLiveData()
    val workspaceList : LiveData<MutableList<WorkspaceInfo>> get() = _workspaceList
    var currentWorkspace : String = ""
    private val context = getApplication<Application>().applicationContext

    init{
        _workspaceList.value = parseDataWorkspace((readFile(context, "workspace.txt")))
        //_workspaceList.value = mutableListOf(WorkspaceInfo(1, "Hi"), WorkspaceInfo(2, "testing"))
        _cardList.value = parseDataCard(readFile(context, "card.txt"))
    }

    fun refresh(){
        _cardList.value = parseDataCard(readFile(context,"card.txt"))
        Log.d("data", "${_cardList.value}")
    }
    private fun readFile(context: Context, fileName: String) : String{
        try{
            context.openFileInput(fileName).use{
                val byteArray = ByteArray(it.available())
                it.read(byteArray)
                return String(byteArray)
            }     } catch (e: IOException){
            e.printStackTrace()
        }
        return ""
    }
    fun deleteCard(id: Int){
        val currentList : MutableList<cardInfo> = _cardList.value.orEmpty().toMutableList()
        currentList.remove(getCardInfo(id))
        _cardList.value = currentList
        writeCard(context,"card.txt", "${_cardList.value}")
    }
    private fun parseDataCard(data: String): MutableList<cardInfo>{
        val infoList = mutableListOf<cardInfo>()
        val regex = """cardInfo\(id=(\d*), item=([^,]*), description=([^)]*), time=([^)]*), color=([^)]*), workspace=([^)]*)\)""".toRegex()
        // ([\d:]+) for items like 7:00
        regex.findAll(data).forEach {
            val (id,item,description,time, color, workspace) = it.destructured
            if(currentWorkspace == workspace.trim()){
            infoList.add(cardInfo(id.toInt(),item.trim(), description.trim(), time.trim(), color.trim(), workspace.trim()))
            }
        }
        return infoList
    }

    private fun parseDataWorkspace(data: String): MutableList<WorkspaceInfo>{
        val infoList = mutableListOf<WorkspaceInfo>()
        val regex = """WorkspaceInfo\(id=(\d*), name=([^,]*)\)""".toRegex()
        // ([\d:]+) for items like 7:00
        regex.findAll(data).forEach {
            val (id,name) = it.destructured
            infoList.add(WorkspaceInfo(id.toInt(),name.trim()))
            currentWorkspace = name.trim()
        }
        return infoList
    }

fun getCardInfo(id: Int): cardInfo {
    val currentList : MutableList<cardInfo> = _cardList.value.orEmpty().toMutableList()
    return currentList[id];
}
    private fun writeCard(context: Context, fileName: String, data: String, append: Boolean = false){
        try{
            val outputStream : FileOutputStream = context.openFileOutput(fileName, if(append) Context.MODE_APPEND else Context.MODE_PRIVATE)
            val writer = OutputStreamWriter(outputStream)
            val bw = BufferedWriter(writer)
            if(append){
                bw.append(data)
            } else{
                bw.write(data)
            }
            bw.close()
        } catch(e: IOException){
            e.printStackTrace()
        }
    }

    fun addCard(cardInfo: cardInfo){
        val currentList : MutableList<cardInfo> = _cardList.value.orEmpty().toMutableList()
        currentList.add(cardInfo)
        _cardList.value = currentList
        writeCard(context, "card.txt", "$cardInfo", true)
    }

    fun addWorkspace(workspaceInfo: WorkspaceInfo){
        val currentList : MutableList<WorkspaceInfo> = _workspaceList.value.orEmpty().toMutableList()
        currentList.add(workspaceInfo)
        _workspaceList.value = currentList
        writeCard(context, "workspace.txt", "$workspaceInfo", true)
    }

    fun modifyCard(updatedCard: cardInfo){
        val currentList : MutableList<cardInfo> = _cardList.value.orEmpty().toMutableList()
        val index = currentList.indexOfFirst {it.id == updatedCard.id}
        if(index != -1){
            currentList[index] = updatedCard
            _cardList.value = currentList.toMutableList()
            writeCard(context,"card.txt" , "${_cardList.value}")
        }
    }

}