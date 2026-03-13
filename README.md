Programmet startes i Main.

Der printes ud i konsollen hver gang producer (BottleProducerThread) lægger en ny flaske
ind i den første queue (BottleQueue).


Derudover printes det ud i konsollen hver gang der afhentes vandflasker (WaterConsumerThread) 
eller ølflasker (BeerConsumerThread).

Begge disse tømmer hele listen ad gangen, da jeg anser det for at være mest realistisk, i forhold
til en varevogn eller lastbil som afhenter alt som er på lager.


Der printes ikke ud når der bliver sorteret (BottleSplitterThread), 
da jeg anså det for allerede at være kaotisk nok i konsollen.



Flasker produceres hurtigere i producer end de sorteres i splitter, 
men da der er fastsat et maksimum i bufferen (BottleQueue) giver det ikke problemer.
Det blev gjort for at vise at den kan håndtere "pres".




Jeg overvejede at bruge et Array, men endte med ikke at ville bruge tid og kræfter på det,
logikken til at holde rede på "head and tail" virkede irriterende.
Så jeg har bare brugt ArrayDeque som jo implementerer Queue for nemhedens skyld.
