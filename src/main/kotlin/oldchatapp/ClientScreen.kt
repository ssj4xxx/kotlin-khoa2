package oldchatapp

import org.apache.commons.lang3.StringUtils

object ClientScreen {
    @JvmStatic
    fun main(args: Array<String>) {
        val string = "Khoa Dang; Duy"
        val s: Unit = StringUtils.substringAfter(string, ";")
        System.out.println(s)
        //dependency management: gradle, maven
    }
}