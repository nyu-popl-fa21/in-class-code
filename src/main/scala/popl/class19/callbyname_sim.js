var y = 0;
const f = function (x: () => Num) {return x() + x()};

const r = f(function () {return y = y + 1});

console.log(r)
console.log(y)
