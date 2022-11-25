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

    override fun run() {
        while (socket.isConnected) {
            try {
                var messageFromAClient = serverReader.readLine()
                broadcastToAll(messageFromAClient)
            } catch (ex: Exception) {
                ex.printStackTrace()
                closeSocket()
            }
        }
    }
    fun broadcastToAll(message: String) {
        for (task in allTasks) {
            try {
                if (!task.taskClientName.equals(taskClientName)) {
                    task.serverWriter.write(taskClientName + ": " + message)
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
        try {
            if (socket != null) {
                socket.close()
            }
            if (serverReader != null) {
                serverReader.close()
            }
            if (serverWriter != null) {
                serverWriter.close()
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

}