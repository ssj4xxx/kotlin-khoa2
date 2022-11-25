package chatapp

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.Socket

var allTasks: MutableList<HandleAClientTask> = mutableListOf()
class HandleAClientTask(private val socket: Socket): Runnable {
    private val serverReader = BufferedReader(InputStreamReader(socket.getInputStream()))
    private val serverWriter = BufferedWriter(OutputStreamWriter(socket.getOutputStream()))
    private val taskClientName = serverReader.readLine()

    //Display message server receive from client
    override fun run() {
        broadcastToAll("has joined the chat")
        while (!socket.isClosed) {
            try {
                val messageFromAClient = serverReader.readLine()
                broadcastToAll(messageFromAClient)
            } catch (ex: Exception) {
                closeSocket()
            }
        }
    }
    fun broadcastToAll(message: String) {
        for (task in allTasks) {
            try {
                if (!task.taskClientName.equals(taskClientName)) { //Display received message to all other clients
                    task.serverWriter.write("$taskClientName: $message")
                    task.serverWriter.newLine()
                    task.serverWriter.flush()
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                println("Cannot print message")
                closeSocket()
            }
        }
    }
    fun closeSocket() {
        if (!socket.isClosed) {
            try {
                this.socket.close()
                this.serverReader.close()
                this.serverWriter.close()
                allTasks.remove(this)
                println("${this.taskClientName} has disconnected")
                broadcastToAll("has left the chat")
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

}