What is it?
-
A minimal working example / reproducer for [TOMEE-4342](https://issues.apache.org/jira/browse/TOMEE-4342)

Background
-
Right now, `ApplicationComposer` driven tests (via `@RunWithApplicationComposer(mode = ExtensionMode.PER_JVM)` do *not* inject `@Resource` beans, declared via Properties in an `@Application` App class. 
Those `Resouce` instances (carrying custom config values) are, however, injected into EJBs or into the App itself.

Interestingly, such resources are injected perfectly fine if the Test is run via the alternative `@RunWithEjbContainer` test container bootstraper.
