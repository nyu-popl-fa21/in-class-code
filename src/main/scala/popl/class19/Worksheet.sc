import popl.class19.repeat

var x = 0                                       
  
repeat {
	x = x + 1
} until(x >= 10)
 
repeat.apply({
 	x = x + 1
}).until(x >= 10)
  
println(x)                                      
