package ch2.currying

// In honor of Haskell Curry

fun <A, B, C> curry(f: (A, B) -> C): (A) -> (B) -> (C) =
    { a: A -> { b: B -> f(a, b) } }