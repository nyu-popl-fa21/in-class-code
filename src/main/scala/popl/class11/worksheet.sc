def sum(f: Int => Int)(a: Int, b: Int): Int = {
  if (a < b) f(a) + sum(f)(a + 1, b) else 0
}

def id(a: Int) = a

def squr(a: Int) = a * a

// (x1: T1, ..., xn: Tn) => e

def sumInts = sum(a => a)_

def sumSqurs = sum(a => a * a)_

sumInts(1, 4)

//sealed abstract class List
//case class Cons(hd: Int, tl: List) extends List
//case object Nil extends List

1 :: 2 :: Nil

def incr(l: List[Int]): List[Int] = {
  l match {
    case Nil => Nil
    case hd :: tl => hd + 1 :: incr(tl)
  }
}

def stretch(l: List[Double], c: Double): List[Double] = {
  l match {
    case Nil => Nil
    case hd :: tl => hd * c :: stretch(tl, c)
  }
}

def map[A,B](f: A => B)(l: List[A]): List[B] = {
  l match {
    case Nil => Nil
    case hd :: tl => f(hd) :: map(f)(tl)
  }
}

map((hd: Int) => (hd.toString + "="))(List(1, 2, 3))

val l = List(1, 2, 3)

l.map(_ + 1)

1 + 2




