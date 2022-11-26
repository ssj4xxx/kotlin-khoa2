package javachatapp

import java.io.*
import java.net.Socket
import java.util.*

//TODO: remove task khi client quit
//TODO: remove client
class HandleAClientTask(private val socket: Socket) : Runnable {
    private val serverReader = BufferedReader(InputStreamReader(socket.getInputStream()))
    private val serverWriter = BufferedWriter(OutputStreamWriter(socket.getOutputStream()))
    private val taskClientName = serverReader.readLine()

    override fun run() {
        broadcastToAll("has joined the chat")
        allTasks.add(this)
        while (!socket!!.isClosed) {
            try {
                val messageFromAClient = serverReader!!.readLine()
                broadcastToAll(messageFromAClient)
            } catch (ex: Exception) {
                closeSocket()
                broadcastToAll("has left the chat")
            }
        }
    }

    fun broadcastToAll(message: String) {
        for (task in allTasks) {
            try {
                if (task.taskClientName != taskClientName) {
                    task.serverWriter!!.write("$taskClientName: $message")
                    task.serverWriter.newLine()
                    task.serverWriter.flush()
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                System.err.println("Cannot print message")
                closeSocket()
            }
        }
    }

    fun closeSocket() {
        try {
            if (!socket!!.isClosed) {
                socket.close()
                serverReader!!.close()
                serverWriter!!.close()
                allTasks.remove(this)
                println(taskClientName + " has disconnected")
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    companion object {
        private val allTasks = Collections.synchronizedList(ArrayList<HandleAClientTask>())
    }
}