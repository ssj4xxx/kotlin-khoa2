package chatapp

class test {
    companion object {
        @JvmStatic
        fun main(Args: Array<String>) {
            val mylamda = Thread {
                for (x in 0..10) {
                    Thread.sleep(200)
                    println("$x")
                }
            }
            mylamda.start()
//            startThread(mylamda)

        }
//        fun startThread(mylamda: Thread) {
//            mylamda.start()
//        }
    }



}