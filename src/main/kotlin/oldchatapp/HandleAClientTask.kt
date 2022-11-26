package oldchatapp

import java.io.*
import java.net.Socket
import java.util.*

//TODO: remove task khi client quit
//TODO: remove client
class HandleAClientTask(socket: Socket) : Runnable {
    private val socket: Socket? = null
    private var serverReader: BufferedReader? = null
    private var serverWriter: BufferedWriter? = null
    var taskClientName: String? = null

    init {
        try {
            this.socket = socket
            serverReader = BufferedReader(InputStreamReader(socket.getInputStream()))
            serverWriter = BufferedWriter(OutputStreamWriter(socket.getOutputStream()))
            taskClientName =
                if (serverReader.readLine() == null) "random" else serverReader.readLine() //TODO: gen random username, doi username
            allTasks.add(this)
        } catch (ex: IOException) {
            ex.printStackTrace()
            closeSocket()
        }
    }

    override fun run() {
        while (socket!!.isConnected) {
            try {
                val messageFromAClient = serverReader!!.readLine()
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
            socket?.close()
            serverReader?.close()
            serverWriter?.close()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    companion object {
        private val allTasks = Collections.synchronizedList(ArrayList<HandleAClientTask>())
    }
}