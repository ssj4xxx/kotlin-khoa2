package chatapp

import java.net.Socket
import java.util.*

class ClientApp {
    fun main(Args: Array<String>) {
        val input = Scanner(System.`in`)
        val socket = Socket("localhost", 1234)
        println("Enter your name")
        val name = input.nextLine()
        val client = Client(socket, name)
        client.sendNameToServer()
        client.sender().start()
        client.receiver().start()
    }
}