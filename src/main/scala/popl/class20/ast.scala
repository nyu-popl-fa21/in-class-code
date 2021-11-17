package popl.class20

import scala.util.parsing.input.Positional
import org.kiama.output.PrettyPrinter

object ast {
  sealed abstract class Expr extends Positional {
    // pretty print as AST
    override def toString(): String = PrettyPrinter.pretty(this)
  }
  
  /* Literals and Values */
  sealed abstract class Val extends Expr
  case class Num(n: Double) extends Val
  case class Addr private[ast] (a: Int) extends Val
  
  /* Variables */
  case class Var(x: String) extends Expr
  
  /* Declarations */
  case class Decl(mut: Mut, x: String, ed: Expr, eb: Expr) extends Expr
  
  /* Mutabilities */
  sealed abstract class Mut
  case object MConst extends Mut
  case object MVar extends Mut
  
  /* Unary and Binary Operators */
  case class UnOp(op: Uop, e1: Expr) extends Expr
  case class BinOp(op: Bop, e1: Expr, e2: Expr) extends Expr

  sealed abstract class Uop
  case object UMinus extends Uop /* - */

  sealed abstract class Bop
  case object Plus extends Bop /* + */
  
  /* Addresses and Mutation */
  case object Assign extends Bop /* = */
  case object Deref extends Uop /* * */
  
  /* Memory */
  class Mem private (map: Map[Addr, Val], nextAddr: Int) {
    def apply(key: Addr): Val = map(key)
    def get(key: Addr): Option[Val] = map.get(key)
    def +(kv: (Addr, Val)): Mem = new Mem(map + kv, nextAddr)
    def contains(key: Addr): Boolean = map.contains(key)
    
    def alloc(v: Val): (Mem, Addr) = {
      val fresha = Addr(nextAddr)
      (new Mem(map + (fresha -> v), nextAddr + 1), fresha)
    }
    
    override def toString: String = map.toString
  }
  
  object Mem {
    def empty = new Mem(Map.empty, 1)
  }
  
  case class StuckError(e: Expr) extends Exception("stuck while evaluating expression")

}