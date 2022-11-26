package oldchatapp

import java.io.IOException
import java.net.Socket
import java.util.*

object ClientApp {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val input = Scanner(System.`in`)
        val socket = Socket("localhost", 1234)
        print("Enter your name: ")
        val name = input.nextLine()
        val client = Client(socket, name)
        client.sendNameToServer()
        client.sender().start()
        client.receiver().start()
    }
}