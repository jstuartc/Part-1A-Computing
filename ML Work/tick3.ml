datatype 'a tree = Lf
					| Br of 'a * 'a tree * 'a tree;

fun tcons (v, Lf) = Br (v, Lf, Lf)
      | tcons (v,(Br (w, t1, t2))) = Br (v, tcons(w,t2), t1);

fun arrayoflist [] = Lf
	| arrayoflist (x::xs) = tcons (x,arrayoflist(xs));


fun top (Br(x,t1,t2)) = x;

fun treeshrinker (Br(_,Lf,Lf)) = Lf
	|treeshrinker (Br(_,t1,t2)) = Br(top(t1),t2,treeshrinker(t1));

fun listofarray Lf = []
	| listofarray (Br(x,t1,t2)) = x::listofarray(treeshrinker(Br(x,t1,t2)));

fun isEven([],_) = []
	|isEven(x::xs,c) = if x mod 2 = 0 then c::isEven(xs,c+1) else isEven(xs,c+1);


fun getSubsOfEvens v = isEven(listofarray(v),1);