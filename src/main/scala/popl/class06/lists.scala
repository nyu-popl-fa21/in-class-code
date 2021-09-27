package popl.class06

// Declaration of list data type
sealed abstract class List {
  def ::(hd: Int): List = popl.class06.::(hd, this)
}

// The empty list
case object Nil extends List

// The constructor for creating a new list from a head `hd` and a tail list `tl`
case class ::(hd: Int, tl: List) extends List {
  override def toString(): String = s"$hd :: $tl"
}

object  lists {
  def length(l: List): Int = l match {
    case Nil => 0
    case hd :: tl => 1 + length(tl)
  }

  def append(l1: List, l2: List): List = l1 match {
    case Nil => l2
    case hd :: tl => hd :: append(tl, l2)
  }

  def reverse(l: List): List = {
    def revLoop(l: List, acc: List): List = l match {
      case Nil => acc
      case hd :: tl => revLoop(tl, hd :: acc)
    }
    revLoop(l, Nil)
  }
}