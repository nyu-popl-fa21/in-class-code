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

  
  def eval(e: Expr): Mem => (Mem, Val) = {
    def eToNum(e: Expr): Mem => (Mem, Double) = { m =>
      val (mp, v) = eval(e)(m)
      v match {
        case Num(n) => (mp, n)
        case _ => throw StuckError(e)
      }
    }
    e match {
      /** rule EvalVal */
      case v: Val => { m => (m, v) }

      /** rule EvalUMinus */
      case UnOp(UMinus, e1) => { m =>
        val (mp, n1) = eToNum(e1)(m)
        (mp, Num(-n1))
      }

      /** rule EvalDeref */
      case UnOp(Deref, a: Addr) => ???

      /** rule EvalPlus */
      case BinOp(Plus, e1, e2) => { m =>
        val (mp, n1) = eToNum(e1)(m)
        val (mpp, n2) = eToNum(e2)(mp)
        (mpp, Num(n1 + n2))
      }

      /** rule EvalAssignVar */
      case BinOp(Assign, UnOp(Deref, a: Addr), e2) => ???

      /** rule EvalConstDecl */
      case Decl(MConst, x, ed, eb) => { m =>
        val (mp, vd) = eval(ed)(m)
        eval(subst(eb, x, vd))(mp)
      }

      /** rule EvalVarDecl */
      case Decl(MVar, x, ed, eb) => { m =>
        val (md, vd) = eval(ed)(m)
        val (mp, a) = md.alloc(vd)
        eval(subst(eb, x, UnOp(Deref, a)))(mp)
      }
    }
  }
}