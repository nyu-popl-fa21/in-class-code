import popl.class26._

abstract class Animal {
  def name: String
  override def toString = name
  def makeNoise: String
}
abstract class Cat extends Animal
object Tiger extends Cat {
  def name = "Tiger"  
  def makeNoise = "Roaaar"
}

abstract class Bird extends Animal
object Duck extends Bird {
  def name = "Duck"
  def makeNoise = "Quack"
}
object Sparrow extends Bird {
  def name = "Sparrow"
  def makeNoise = "Peep"
}

val q : Queue[Bird] = Queue(Duck)
  
q.enqueue(Sparrow).enqueue(Tiger).dequeue._1.makeNoise
