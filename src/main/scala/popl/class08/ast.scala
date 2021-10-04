package popl.class08

object ast {
  sealed abstract class Expr
  case class Num(n: Double) extends Expr
  case class Var(x: String) extends Expr
  case class BinOp(bop: Bop, e1: Expr, e2: Expr) extends Expr
  case class ConstDecl(x: String, ed: Expr, eb: Expr) extends Expr

  sealed abstract class Bop
  case object Plus extends Bop
  case object Mult extends Bop

  def ov(e: Expr): Set[String] = e match {
    case Num(n) => Set()
    case Var(x) => Set(x)
    case BinOp(_, e1, e2) => ov(e1) ++ ov(e2)
    case ConstDecl(x, ed, eb) => Set(x) ++ ov(ed) ++ ov(eb)
  }

  def fv(e: Expr): Set[String] = e match {
    case Num(n) => Set()
    case Var(x) => Set(x)
    case BinOp(_, e1, e2) => fv(e1) ++ fv(e2)
    case ConstDecl(x, ed, eb) => (fv(eb) -- Set(x)) ++ fv(ed)
  }

  def bv(e: Expr): Set[String] = e match {
    case Num(n) => Set()
    case Var(x) => Set()
    case BinOp(_, e1, e2) => bv(e1) ++ bv(e2)
    case ConstDecl(x, ed, eb) => Set(x) ++ bv(ed) ++ bv(eb)
  }

  type Env = Map[String, Double]
  def dom(env: Env): Set[String] = env.keySet

  def eval(env: Env, e: Expr): Double = {
    require (fv(e) subsetOf dom(env))
    e match {
      case Num(n) => n
      case Var(x) => env(x)
      case BinOp(Plus, e1, e2) =>
        eval(env, e1) + eval(env, e2)
      case BinOp(Mult, e1, e2) =>
        eval(env, e1) * eval(env, e2)
      case ConstDecl(x, ed, eb) =>
        val n = eval(env, ed)
        eval(env + (x -> n), eb)
    }
  }
}
