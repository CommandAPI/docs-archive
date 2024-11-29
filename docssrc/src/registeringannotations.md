# Registering annotation-based commands

Registering annotation-based commands is _really_ simple. To do this, we use the following method, where `className` is the name of a class with a `@Command` annotation:

```java
CommandAPI.registerCommand(className)
```

<div class="example">

### Example: Registering a Warp command

Say we have a simple command `/warp` that is defined as follows:

```java
{{#include ../../commandapi-documentation-code/src/main/java/dev/jorel/commandapi/examples/java/Examples.java:annotationsintro2}}
```

We can register this in our `onLoad()` method so we can use this command from within Minecraft functions:

```java
{{#include ../../commandapi-documentation-code/src/main/java/dev/jorel/commandapi/examples/java/Examples.java:registeringannotations2}}
```

</div>
