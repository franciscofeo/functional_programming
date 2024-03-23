package ch2.sort

// Implement isSorted()
fun main() {
    val array = arrayOf(1, 2, 3, 4, 5, 5, 5)
    val genericIsSorted = isSorted(array) { a, b ->  a >= b }
    println(genericIsSorted)
}

private fun isSortedArrayInt(arr: Array<Int>): Boolean {
    fun loop(pos: Int, acc: Boolean): Boolean =
        when {
            pos == arr.size -> acc
            arr[pos] >= arr[pos - 1] -> loop(pos + 1, true)
            else -> false
        }
    return loop(1, true)
}

private fun <A> isSorted(arr: Array<A>, pred: (A, A) -> Boolean): Boolean{
    fun loop(pos: Int, acc: Boolean): Boolean =
        when{
            pos == arr.size -> acc
            pred(arr[pos], arr[pos - 1]) -> loop(pos + 1, true)
            else -> false
        }
    return loop(1, true)
}