package chatapp

import java.net.ServerSocket

class Server(private val port: Int): Runnable {
    private val serverSocket = ServerSocket(port)
    override fun run() {
        try {
            while (!serverSocket.isClosed) {
                val socket = serverSocket.accept()
                println("A new client has connected")
                val task = HandleAClientTask(socket)
                allTasks.add(task)
                Thread(task).start()
            }
        } catch (ex: Exception) {
            println("Cannot run server")
            ex.printStackTrace()
        }
    }
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val server = Thread(Server(1234))
            server.start()
            println("Server starts")
        }
    }
}
