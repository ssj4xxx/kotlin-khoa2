package chatapp

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception
import java.net.Socket
import java.util.Scanner

class Client(private val socket: Socket, private val username: String) {
    private val clientReader = BufferedReader(InputStreamReader(socket.getInputStream()))
    private val clientWriter = BufferedWriter(OutputStreamWriter(socket.getOutputStream()))

    val sender = Thread {
        val input = Scanner(System.`in`)
        while (socket.isConnected) {
            try {
                clientWriter.write(input.nextLine())
                clientWriter.newLine()
                clientWriter.flush()
            } catch (ex: Exception) {
                println("Error while sending message")
                ex.printStackTrace()
            }
        }
    }

    val receiver = Thread {
        while (socket.isConnected) {
            try {
                println(clientReader.readLine())
            } catch (ex: Exception) {
                println("Error receiving message")
                ex.printStackTrace()
            }
        }
    }

    fun sendNameToServer() {
        try {
            clientWriter.write(username)
            clientWriter.newLine()
            clientWriter.flush()
        } catch (ex: Exception) {
            println("Error while sending name")
            ex.printStackTrace()
        }
    }
}