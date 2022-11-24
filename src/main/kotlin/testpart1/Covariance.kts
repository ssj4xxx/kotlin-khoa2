package testpart1

open class Fruit
class Banana : Fruit()
class Orange: Fruit()
fun receiveFruits(fruits: List<Fruit>) {
    println("Number of fruits: ${fruits.size}")
}
val bananas: List<Banana> = listOf()
val fruits1: List<Fruit> = listOf()
receiveFruits(fruits1)
receiveFruits(bananas) //ERROR: type mismatch
fun copyFromTo(from: Array<out Fruit>, to: Array<Fruit>) {
    for (i in 0 until from.size) {
        to[i] = from[i]
    }
}
val fruitsBasket1 = Array<Fruit>(3) { _ -> Fruit() }
val fruitsBasket2 = Array<Fruit>(3) { _ -> Fruit() }
copyFromTo(fruitsBasket1, fruitsBasket2)
val fruitsBasket = Array<Fruit>(3) { _ -> Fruit() }
val bananaBasket = Array<Banana>(3) { _ -> Banana() }
copyFromTo(bananaBasket, fruitsBasket) //ERROR: type mismatch
