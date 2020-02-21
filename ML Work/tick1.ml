fun evalquad (a,b,c,x) : real = a*x*x + b*x + c;

fun facr(n) = if n >=0 then (if n = 0 then 1 else facr(n-1)*n) else 0 ;

fun faci2(n,total) = if n = 0 then total else faci2(n-1,total*n);

fun faci(n) = if n >=0 then faci2(n,1) else 0 ;


fun sumtD(n,total)  = if n=0 then total else sumtD(n-1,total/2.0);

fun sumt(n:int):real = if n>=0 then 2.0 -sumtD(n-1,1.0) else 0.0;