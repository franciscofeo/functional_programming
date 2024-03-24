package ch3

sealed class List<out A> {

    companion object {
        fun <A> of(vararg args: A): List<A> {
            val tail = args.sliceArray(1 until args.size)
            return if (args.isEmpty()) Nil else Cons(args.first(), of(*tail))
        }

        fun sum(list: List<Int>): Int =
            when (list) {
                is Nil -> 0
                is Cons -> list.head + sum(list.tail)
            }

        fun product(list: List<Double>): Double =
            when (list) {
                is Nil -> 1.0
                is Cons -> if (list.head == 0.0) 0.0 else list.head * product(list.tail)
            }


        // Generalize sum and product functions
        fun <A, B> foldRight(list: List<A>, default: B, pred: (A, B) -> B): B =
            when (list) {
                is Nil -> default
                is Cons -> pred(list.head, foldRight(list.tail, default, pred))
            }
    }
}

data object Nil : List<Nothing>()

// Cons(head=1, tail=Cons(head=2, tail=Cons(head=3, tail=Cons(head=4, tail=Nil))))
data class Cons<out A>(val head: A, val tail: List<A>) : List<A>()

fun main() {
    val list = List.of(1, 2, 3)
    println("List: $list")
    println("Sum: ${List.sum(list)}")
    println("Product: ${List.product(List.of(1.0, 2.0, 3.0))}")
}
