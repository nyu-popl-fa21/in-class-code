import popl.class04.ast._

val e1 = BinOp(Mult, Num(4), Num(0))

val e2 = BinOp(Plus, Num(3), e1)
	
val e3 = BinOp(Mult, Num(4), Num(0))
	
e1 == e3
	
e1 eq e3
	
e3.bop
	
e3.copy(bop = Plus)
	
val e = Num(0)
	
simplifyTop(e1)

pretty(e2)
