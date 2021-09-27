package popl.class05

object numbers {
  sealed abstract class N
  case object Zero extends N
  case class Succ(x: N) extends N

  def D(x: N): Int = x match {
    case Zero => 0
    case Succ(xp) => 1 + D(xp)
  }

  def plus(x: N, y: N): N = x match {
    case Zero => y
    case Succ(xp) => plus(xp, Succ(y))
  }
}
