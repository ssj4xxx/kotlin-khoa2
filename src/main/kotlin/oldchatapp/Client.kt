package oldchatapp

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.Socket
import java.util.*

class Client(var socket: Socket, var username: String) {
    var clientReader: BufferedReader
    var clientWriter: BufferedWriter

    init {
        clientReader = BufferedReader(InputStreamReader(socket.getInputStream()))
        clientWriter = BufferedWriter(OutputStreamWriter(socket.getOutputStream()))
    }

    fun sender(): Thread {
        return Thread {
            val input = Scanner(System.`in`)
            while (socket.isConnected) {
                try {
                    clientWriter.write(input.nextLine())
                    clientWriter.newLine()
                    clientWriter.flush()
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        }
    }

    fun receiver(): Thread {
        return Thread {
            while (socket.isConnected) {
                try {
                    println(clientReader.readLine())
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        }
    }

    fun sendNameToServer() {
        try {
            clientWriter.write(username)
            clientWriter.newLine()
            clientWriter.flush()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}