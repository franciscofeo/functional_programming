package ch3.ex1_ex2

sealed class List<out A>{

    companion object{
        fun <A> of(vararg args: A): List<A> {
            val tail = args.sliceArray(1 until args.size)
            return if(args.isEmpty()) Nil else Cons(args.first(), of(*tail))
        }

        // Remove an element from the head -> O(1)
        fun <A> tail(list: List<A>): List<A> =
            when(list){
                is Nil -> Nil
                is Cons -> list.tail
            }

        // Create a new element in the head -> O(1)
        fun <A> setHead(list: List<A>, h: A): List<A> =
            when(list){
                is Nil -> Nil
                is Cons -> Cons(h, list)
            }

        // Update the head value -> O(1)
        fun <A> updateHead(list: List<A>, h: A): List<A> =
            when(list){
                is Nil -> Nil
                is Cons -> Cons(h, list.tail)
            }
    }
}

data object Nil: List<Nothing>()

// Cons(head=1, tail=Cons(head=2, tail=Cons(head=3, tail=Cons(head=4, tail=Nil))))
data class Cons<out A>(val head: A, val tail: List<A>): List<A>()

fun main(){
    val list = List.of(1, 2, 3, 4)
    println("List: $list")
    println("List with the first element removed: ${List.tail(list)}")
    println("List with a new head: ${List.setHead(list, 9)}")
    println("List with an updated head: ${List.updateHead(list, 120)}")
}