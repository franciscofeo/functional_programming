package ch2.fibonacci

// nth Fibonacci's number
fun fib(i: Int): Int {
    fun loop(count: Int, acc: Int, next: Int): Int =
        if(count <= 0) acc
        else loop(count - 1, next, acc + next)
    return loop(i, 0, 1)
}