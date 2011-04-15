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
* Add Record types, see records branch (only local ;-).
* Make maps implement Fn&lt;T,V&gt;.
* Make maps implement Predicate&lt;T&gt;.
* Make set implement Predicate&lt;T&gt;.
* Make vectors implement Fn&lt;Integer,T&gt; and Predicate&lt;Integer&gt;.
* Add function composition to the Functions class.
* Implement a sorted maps and sets based on red-black-trees.
* Implement a stack.
* Implement a Queue.
