val l = 1 :: 2 :: 3 :: 4 :: Nil

def map[A,B](l: List[A])(op: A => B): List[B] = {
  l match {
    case Nil => Nil
    case hd :: tl => op(hd) :: map(tl)(op)
  }
}

def incr(l: List[Int]) = map(l)(1 + _)

incr(l)

def sum(l: List[Int]): Int = l match {
  case Nil => 0
  case hd :: tl => hd + sum(tl)
}

sum(l)

def foldRight[A,B](l: List[A])(z: B)(op: (A,B) => B): B =
  l match {
    case Nil => z
    case hd :: tl => op(hd, foldRight(tl)(z)(op))
  }

def sum2(l: List[Int]) = foldRight(l)(0)(_ + _)

sum2(l)

def length[A](l: List[A]): Int = foldRight(l)(0)((_, x) => 1 + x)

/*
def map[A,B](l: List[A])(op: A => B): List[B] = {
  l match {
    case Nil => Nil
    case hd :: tl => op(hd) :: map(tl)(op)
  }
}
*/

def map2[A,B](l: List[A])(op: A => B): List[B] =
  foldRight(l)(Nil : List[B])(op(_) :: _)

map2(l)(1 + _)

def append[A](l1: List[A], l2: List[A]) =
  foldRight(l1)(l2)(_ :: _)

append(l, l)

def foldLeft[A,B](l: List[A])(z: B)(op: (B,A) => B): B = l match {
  case Nil => z
  case hd :: tl => foldLeft(tl)(op(z, hd))(op)
}

def sum3(l: List[Int]) = foldLeft(l)(0)(_ + _)

def reverse[A](l: List[A]): List[A] =
  foldLeft(l)(Nil: List[A])((rev_prefix, hd) => hd :: rev_prefix)

reverse(l)

def reverse[A](l: List[A]): List[A] =
  foldRight(l)(Nil: List[A])((hd, rev_tail) => rev_tail ++ (hd :: Nil))
