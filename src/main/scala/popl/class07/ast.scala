package popl.class07

object ast {
  sealed abstract class Expr
  case class Num(n: Double) extends Expr
  case class Var(x: String) extends Expr
  case class BinOp(bop: Bop, e1: Expr, e2: Expr) extends Expr

  sealed abstract class Bop
  case object Plus extends Bop
  case object Mult extends Bop

  def ov(e: Expr): Set[String] = e match {
    case Num(n) => Set()
    case Var(x) => Set(x)
    case BinOp(_, e1, e2) => ov(e1) ++ ov(e2)
  }

  type Env = Map[String, Double]
  def dom(env: Env): Set[String] = env.keySet

  def eval(env: Env, e: Expr): Double = {
    require (ov(e) subsetOf dom(env))
    e match {
      case Num(n) => n
      case Var(x) => env(x)
      case BinOp(Plus, e1, e2) =>
        eval(env, e1) + eval(env, e2)
      case BinOp(Mult, e1, e2) =>
        eval(env, e1) * eval(env, e2)
    }
  }
}
