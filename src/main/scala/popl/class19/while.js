const while = function while(name cond: bool): 
	  (name Undefined) => Undefined
  {
    return function(name body: Undefined) {
      return cond ? (body, while(cond)(body)) : undefined;
    }
  };
  
  
var x = 0;
while(x < 10)({
  console.log(x);
  x = x + 1;
  undefined
})