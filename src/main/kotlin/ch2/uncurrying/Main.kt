package ch2.uncurrying

fun <A, B, C> uncurry(f: (A) -> (B) -> (C)): (A, B) -> C =
    {a: A, b: B -> f(a)(b) }

fun <A, B, C> uncurry2(f: (A) -> (B) -> (C)): (A, B) -> C =
    {a: A, b: B -> f(a).invoke(b) }
