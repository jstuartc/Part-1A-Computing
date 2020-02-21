fun nfold f 0 x = x
	|nfold f 1 x = f(x)
	|nfold f n x = f(nfold f (n-1) x); 
	
fun sum m 0 = m
	|sum m n = if n >0 then nfold (fn x=>x+1) n m else nfold (fn x=>x-1) (~n) m;

fun product m 1 = m
	|product m 0 = 0
	|product m n = if n>0 then nfold (fn x => x+ m) (n-1) m else (~(nfold (fn x=> x+m) ((~n-1)) m));

fun power m 1 = m
	|power m 0 = 1
	|power m n = nfold (fn x => x*m) (n-1) m;

datatype 'a stream = Cons of 'a * (unit -> 'a stream);
fun from k = Cons(k, fn()=> from(k+1));

fun nth(Cons(x,xq),1) = x
	|nth(Cons(x,xq),n) = nth(xq(),n-1);

fun sCreator(k)= Cons(k*k, fn()=> sCreator(k+1));
val squares = sCreator(1);
nth(squares,49);

fun map2 f (Cons(x,xq)) (Cons(y,yq)) = Cons(f x y,fn() => map2 f (xq()) (yq()));