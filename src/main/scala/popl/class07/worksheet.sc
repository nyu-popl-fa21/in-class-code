import popl.class07.ast._

// (x * 3) + y
val e = BinOp(Plus, BinOp(Mult, Var("x"), Num(3)),
                    Var("y"))

ov(e)

val env = Map("x" -> 1.0, "y" -> 2)

eval(env, e)



