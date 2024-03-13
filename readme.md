# K3M (Kotlin Multi Module Mapper)

## K3M-CORE

This artifact is a map function generator based on KSP (Kotlin Symbol Processing). It is not intended for use by the end user of the library, but rather serves as a code generation tool.

**To implement the functionality, you need to:**

1. **K3MSymbolProcessor:**

* Create a class that inherits from `K3MSymbolProcessor`.
* Implement the `processMaps` function, which should return a list of `MapperInfo` objects.

2. **SymbolProcessorProvider:**

* Create a class that inherits from `SymbolProcessorProvider`.
* Implement the `create` function, which should provide all the necessary dependencies for your `K3MSymbolProcessor`.

3. **File META-INF/services/com.google.devtools.ksp.processing.SymbolProcessorProvider:**

* Create a file `resources/META-INF/services/com.google.devtools.ksp.processing.SymbolProcessorProvider`.
* In the file, specify the name of your `SymbolProcessorProvider`.

**Functionality**

1. Generation of multiple map functions for a single relationship (Source - Destination). The only differences are in the dependencies.
2. Adding dependencies for conversion
