import java.util.Scanner

fun main() {
    val input = Scanner(System.`in`)
    print("Enter the number: ")
    val number = input.nextLine()
    println(if (isPalindrome(number)) "Yes, your number is Palindrome"
    else "No, the number is not Palindrome")
}
fun isPalindrome(number: String): Boolean = number == number.reversed()