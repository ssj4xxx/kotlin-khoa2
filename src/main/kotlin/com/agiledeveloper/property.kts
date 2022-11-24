class Car(var yearOfMake: Int)
val car = Car(2019)
println(car.yearOfMake) //2019
car.yearOfMake = 2022
println(car.yearOfMake)
fun useCarObject(): Pair<Int, String> {
    val car = Car(2019, "Red")
    val year = car.yearOfMake
    car.color = "Green"
    val color = car.color
    return year to color
}
useCarObject()