package popl.class04

object ast {
   
  sealed abstract class Expr
  case class Num(n: Int) extends Expr
  case class BinOp(bop: Bop, e1: Expr, e2: Expr) extends Expr
  
  sealed abstract class Bop
  case object Plus extends Bop
  case object Mult extends Bop
    
  def simplifyTop(e: Expr): Expr =
    e match {
    case BinOp(Mult, _, e2 @ Num(0)) => e2
    case BinOp(Mult, e1, Num(1)) => e1
    case BinOp(Plus, e1, Num(0)) => e1
    case BinOp(Plus, e1, e2) if e1 == e2 => 
      BinOp(Mult, Num(2), e1)
    case _ => e
  }
    
  def pretty(e: Expr): String = e match {
    case Num(n) => n.toString
    case BinOp(bop, e1, e2) =>
      val bop_str = bop match {
        case Mult => " * "
        case Plus => " + "
      }
      "(" + pretty(e1) + bop_str + pretty(e2) + ")"
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}