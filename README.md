Changeless - An Immutable Collection Library for Java
=====================================================

Introduction
------------
Changeless is an immutable collection library for Java inspired by 
[Clojure](http://www.clojure.org/ "Clojure"). The library is not ready for 
release yet, it still needs some more documentation and enhancements in a few 
places. The public API should be stable by now, and will only change in a 
non-backward compatible manner if it is absolutely necessary. 

Any help would be highly appreciated. 

License
-------
This project is licensed under 
[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0 "Apache License, Version 2.0"). 
You can find the license file for the project 
[here](https://github.com/sunesimonsen/changeless/raw/master/LICENSE.txt "License").

Javadoc
-------
You can find Javadoc for the project [here](http://sunesimonsen.github.com/changeless/ "Javadoc"). 

TODO
----
* Make methods covariant and contravariant where appropriate.
* Document all public methods with Javadoc.
* Go through generated Javadoc and find things that looks weird and fix them.
* Add examples in Markdown format. 
* Add Record types (Done) 
* Make maps implement Fn&lt;T,V&gt;.
* Make maps implement Predicate&lt;T&gt;.
* Make set implement Predicate&lt;T&gt;.
* Make vectors implement Fn&lt;Integer,T&gt; and Predicate&lt;Integer&gt;.
* Add function composition to the Functions class.
* Implement sorted maps and sets based on red-black-trees.
* Implement a stack.
* Implement a Queue.            

Acknowledgements
----------------
The implementation of an immutable hash-trie in the
[com.jayway.collections.internal.hashtrie](https://github.com/sunesimonsen/changeless/tree/master/src/main/java/com/jayway/changeless/internal/hashtrie "com.jayway.collections.internal.hashtrie") 
package is based on Daniel Spiewak's 'HashTrie' implementation for Scala that is
in turn a clean-room port of Rich Hickey's persistent hash trie implementation
from Clojure (http://clojure.org). Originally presented as a mutable structure
in a paper by Phil Bagwell. The implementation is also heavily inspired by
Eugene Vigdorchik's blog post:
http://groovy.dzone.com/articles/immutable-data-structures

All the data-structures are also inspire by the data-structures in Clojure.
