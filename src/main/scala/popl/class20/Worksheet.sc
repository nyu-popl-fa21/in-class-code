  
val l1 = List(1, 3)                             
val l2 = List("a", "b", "c")                   
  
for { x <- l1 } yield x + 1
// l1.map(x => x + 1)

// for { x1 <- l1; ...; xn <- ln } yield e
// l1.flatMap(x1 => l2.flatMap(x2 => .... (ln.map(xn => e))...)

for {
  x1 <- l1
  x2 <- l2
} yield (x1, x2)

for {
  x <- Some(1)
} yield x + 1

// Future

l1.flatMap(x1 => l2.map(x2 => (x1, x2)))

//l1.map(x1 => l2.map(x2 => (x1, x2))).flatten

def flatMap[A,B](l: List[A])(op: A => List[B]): List[B] = {
  l.foldLeft(Nil: List[B])((acc, x) => acc ++ op(x))
}


