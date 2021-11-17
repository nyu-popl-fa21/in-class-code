package popl.class20

object interpreter {
  import ast._
  
  /* 
   * Substitutions e[er/x]
   */
  def subst(e: Expr, x: String, er: Expr): Expr = {
    /* Simple helper that calls substitute on an expression
     * with the input value v and variable name x. */
    def substX(e: Expr): Expr = subst(e, x, er)
    /* Body */
    e match {
      case Num(_) | Addr(_) => e
      case Var(y) => if(x == y) er else e
      case UnOp(uop, e1) => UnOp(uop, substX(e1))
      case BinOp(bop, e1, e2) => BinOp(bop, substX(e1), substX(e2))
      case Decl(mut, y, ed, eb) => 
        Decl(mut, y, substX(ed), if (x == y) eb else substX(eb))
    }
  }

  
  def eval(m: Mem, e: Expr): (Mem, Val) = {
    def eToNum(m: Mem, e: Expr): (Mem, Double) = {
      val (mp, v) = eval(m, e)
      v match {
        case Num(n) => (mp, n)
        case _ => throw StuckError(e)
      }
    }
    e match {
      /** rule EvalVal */
      case v: Val => (m, v)
      
      /** rule EvalUMinus */
      case UnOp(UMinus, e1) =>
        val (mp, n1) = eToNum(m, e1)
        (mp, Num(- n1))
      
      /** rule EvalDeref */
      case UnOp(Deref, a: Addr) => ???
      
      /** rule EvalPlus */
      case BinOp(Plus, e1, e2) =>
        val (mp, n1) = eToNum(m, e1)
        val (mpp, n2) = eToNum(mp, e2)
        (mpp, Num(n1 + n2))
      
      /** rule EvalAssignVar */
      case BinOp(Assign, UnOp(Deref, a: Addr), e2) => ???       
      
      /** rule EvalConstDecl */
      case Decl(MConst, x, ed, eb) =>
        val (mp, vd) = eval(m, ed)
        eval(mp, subst(eb, x, vd))
      
      /** rule EvalVarDecl */
      case Decl(MVar, x, ed, eb) =>
        val (md, vd) = eval(m, ed)
        val (mp, a) = md.alloc(vd)
        eval(mp, subst(eb, x, UnOp(Deref, a)))
        
      case Var(_) | UnOp(Deref, _) | BinOp(_, _, _) => throw StuckError(e)
    }
  }
}