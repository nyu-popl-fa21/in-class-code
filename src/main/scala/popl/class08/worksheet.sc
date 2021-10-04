import popl.class08.ast._

// (x * 3) + y
val e = BinOp(Plus, BinOp(Mult, Var("x"), Num(3)),
                    Var("y"))

ov(e)

val env = Map("x" -> 1.0, "y" -> 2.0)

eval(env, e)

val e1 = ConstDecl("x", BinOp(Mult, Var("x"), Num(3)),
  BinOp(Plus, Var("x"), Var("y")))

eval(env, e1)

