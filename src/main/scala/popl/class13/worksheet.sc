val v1 = List(1, 2, 3)

val v2 = List(2, 3, 4)

v1.lazyZip(v2).map(_ * _).foldLeft(0)(_ + _)


