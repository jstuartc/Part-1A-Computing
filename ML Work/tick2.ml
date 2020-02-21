fun null [] = true
	| null (x::l) = false

fun last (x::xs:'a list):'a = if null(xs) = true then x else last(xs);

fun butlast (x::xs) = if null(xs) = true then [] else x::butlast(xs);
	
fun nth(x::xs,n) = if n = 0 then x else nth(xs,n-1);
	
