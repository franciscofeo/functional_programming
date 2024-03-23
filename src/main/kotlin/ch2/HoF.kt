package ch2

fun main() {
    printResult("Multiply", 2, 5) { x, y -> x * y }
    println("First implementation: ${findFirst(arrayOf("A", "B", "C"), "D")}")
    val genericFindFirst = genericFindFirst(arrayOf("A", "B", "C")) { it == "D" }
    println("Generic implementation: $genericFindFirst")
}

private fun printResult(opName: String, x: Int, y: Int, f: (Int, Int) -> Number) {
    println("Operation: $opName")
    println("Result: ${f(x, y)}")
}

private fun findFirst(arr: Array<String>, key: String): Int {
    tailrec fun loop(pos: Int): Int =
        when {
            pos >= arr.size -> -1
            arr[pos] == key -> pos
            else -> loop(pos + 1)
        }
    return loop(0)
}

private fun <A> genericFindFirst(arr: Array<A>, pred: (A) -> Boolean): Int {
    tailrec fun loop(pos: Int): Int =
        when {
            pos >= arr.size -> -1
            pred(arr[pos]) -> pos
            else -> loop(pos + 1)
        }
    return loop(0)
}