package testpart1

fun max(numbers: IntArray): Int {
    var large = Int.MIN_VALUE
    for (number in numbers) {
        large = if (number > large) number else large
    }
    return large
}
println(max(intArrayOf(1, 2, 4 , 75, 54, 12)))