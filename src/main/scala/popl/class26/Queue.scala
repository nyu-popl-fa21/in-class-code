package popl.class26

class Queue[+T](val elems: List[T]) {
  
  def enqueue[U >: T](e: U): Queue[U] = 
    new Queue(elems :+ e)
  
  def dequeue: (T, Queue[T]) = {
    require (!isEmpty)
    val x :: elems1 = elems
    (x, new Queue(elems1))
  }
  
  def head: T = 
    elems.reverse.head
  
  def tail: Queue[T] = {
    new Queue(elems.dropRight(1))
  }
  
  def isEmpty: Boolean = elems.isEmpty
  
  override def toString: String = 
    elems.mkString("Queue(", ", ", ")")
}


object Queue {
  def empty[T]: Queue[T] = new Queue(Nil)
  
  def apply[T](elems: T*): Queue[T] = new Queue(elems.toList)
}

object Test extends App {

  val q = Queue(2, 3).enqueue(1)
  
  q.enqueue("banana")

}
