import popl.class06._
import lists._

val l = 1 :: Nil

l match {
  case Nil => println("empty")
  case hd :: tl => println(s"non-empty list with head $hd")
}

length(append (1 :: 2 :: Nil, 0 :: Nil))

reverse(1 :: 2 :: 3 :: Nil)



