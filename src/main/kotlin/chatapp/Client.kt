package chatapp

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception
import java.net.Socket
import kotlin.concurrent.thread

class Client(val socket: Socket, val username: String) {
    private val clientReader = BufferedReader(InputStreamReader(socket.getInputStream()))
    private val clientWriter = BufferedWriter(OutputStreamWriter(socket.getOutputStream()))
    fun sender(): Thread {

        return TODO("Provide the return value")
    }
    fun receiver(): Thread {
        return TODO("Provide the return value")
    }
    fun sendNameToServer() {
        try {
            clientWriter.write(username)
            clientWriter.newLine()
            clientWriter.flush()
        } catch (ex: Exception) {
            println("Send name error")
            ex.printStackTrace()
        }
    }
}