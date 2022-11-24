package chatapp

import java.net.ServerSocket

class Server(val port: Int): Runnable {
    val serverSocket = ServerSocket(port)
    override fun run() {
        try {
            while (!serverSocket.isClosed) {
                val socket = serverSocket.accept()
                println("A new client has connected")
                val task = HandleAClientTask(socket)
                Thread(task).start()
            }
        } catch (ex: Exception) {
            println("Cannot run server")
            ex.printStackTrace()
        }
    }
    fun main(args: Array<String>) {
        val server = Thread(Server(1234))
        server.start()
        println("Server starts")
    }
}