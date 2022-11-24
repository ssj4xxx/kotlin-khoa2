package chatapp

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.Socket



class HandleAClientTask(private val socket: Socket): Runnable {
    private val serverReader = BufferedReader(InputStreamReader(socket.getInputStream()))
    private val serverWriter = BufferedWriter(OutputStreamWriter(socket.getOutputStream()))
    private val taskClientName = serverReader.readLine()

    override fun run() {
        TODO("Not yet implemented")
    }

}