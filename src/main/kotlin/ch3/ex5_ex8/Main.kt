package ch3.ex5_ex8

import ch3.List.Companion


sealed class List<out A> {

    companion object {
        fun <A> of(vararg args: A): List<A> {
            val tail = args.sliceArray(1 until args.size)
            return if (args.isEmpty()) Nil else Cons(args.first(), of(*tail))
        }

        // Remove an element from the head -> O(1)
        fun <A> tail(list: List<A>): List<A> =
            when (list) {
                is Nil -> Nil
                is Cons -> list.tail
            }

        // Create a new element in the head -> O(1)
        fun <A> setHead(list: List<A>, h: A): List<A> =
            when (list) {
                is Nil -> Nil
                is Cons -> Cons(h, list)
            }

        // Update the head value -> O(1)
        fun <A> updateHead(list: List<A>, h: A): List<A> =
            when (list) {
                is Nil -> Nil
                is Cons -> Cons(h, list.tail)
            }

        fun <A> drop(list: List<A>, n: Int): List<A> =
            when (list) {
                is Nil -> Nil
                is Cons -> {
                    if (n <= 0) list
                    else drop(list.tail, n - 1)
                }
            }

        fun <A> dropWhile(list: List<A>, pred: (A) -> Boolean): List<A> =
            when(list){
                is Nil -> Nil
                is Cons -> {
                    if(pred(list.head)) list
                    else dropWhile(list.tail, pred)
                }
            }

        fun <A> init(list: List<A>): List<A> =
            when(list){
                is Nil -> Nil
                is Cons -> {
                    if (list.tail is Nil) Nil
                    else Cons(list.head, init(list.tail))
                }
            }

        // Generalize sum and product methods
        fun <A, B> foldRight(list: List<A>, default: B, pred: (A, B) -> B): B =
            when (list) {
                is Nil -> default
                is Cons -> pred(list.head, foldRight(list.tail, default, pred))
            }

        // Exercise 3.8
        fun <A> length(list: List<A>): Int =
            when(list){
                is Nil -> 0
                is Cons -> foldRight(list, 0) {a, b -> (a as Number).}
            }

    }
}

data object Nil : List<Nothing>()

// Cons(head=1, tail=Cons(head=2, tail=Cons(head=3, tail=Cons(head=4, tail=Nil))))
data class Cons<out A>(val head: A, val tail: List<A>) : List<A>()