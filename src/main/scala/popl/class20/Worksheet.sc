  
val l1 = List(1, 3)                             
val l2 = List("a", "b", "c")                   
  
for { x <- l1 } yield x + 1                     
// l1 map { x => x + 1 }

for { x <- l1; y <- l2 } yield (x, y)     
// l1 map { x => l2 map { y => (x, y) } } ?
	
def flatMap[A,B](l: List[A])(op: A => List[B]): List[B] =
	l.foldRight(Nil: List[B]){case (x, acc) => op(x) ++ acc }
                                                  
		
flatMap(l1){ x => List(x, x + 1) }       
		
l1 flatMap { x => l2 map { y => (x, y) } }      
		
for { x <- Some(1) } yield x + 1          
