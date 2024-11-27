# Chat preview

![Chat preview](./images/chatpreview.gif)

Chat preview is a feature introduced in Minecraft 1.19 that allows the server to display a preview of a chat message to the client before the client sends their message to the server. This chat preview feature is also compatible with `/say` and `/msg`, as well as the `ChatArgument` and `AdventureChatArgument` classes.

<div class="warning">

## Minecraft version support

The chat preview feature is only present in Minecraft versions 1.19, 1.19.1 and 1.19.2. [Chat preview was removed in 1.19.3](https://minecraft.wiki/w/Java_Edition_1.19.3#General_2), so this feature is unfortunately no longer usable in Minecraft 1.19.3 and beyond.

</div>

-----

## Enabling chat preview

To use chat preview, your server must have `previews-chat` set to `true` in the `server.properties` file:

```properties
...
previews-chat=true
...
```

For players that want to use chat preview, they must have `Chat Preview` enabled in `Options > Chat Settings...`

-----

## Specifying a chat preview function

The `ChatArgument` and `AdventureChatArgument` classes include a method, `withPreview`:

```java
public T withPreview(PreviewableFunction preview);
```

The method `withPreview(PreviewableFunction preview)` lets you generate a preview to send to the client. This method takes in the `PreviewableFunction` functional interface, which is a function that takes in a `PreviewInfo` and returns either a `BaseComponent[]` (for `ChatArgument`) or a `Component` (for `AdventureChatArgument`):

```java
public T generatePreview(PreviewInfo info) throws WrapperCommandSyntaxException;
```

The `PreviewInfo` class is a record containing the following:

```java
public record PreviewInfo<T> {
    Player player();
    String input();
    String fullInput();
    T parsedInput();
}
```

The following methods are as follows:

```java
Player player();
```

`player()` is the player that is currently typing a chat preview.

-----

```java
String input();
```

`input()` is the current input for the current `ChatArgument` or `AdventureChatArgument`. If a user is typing `/mycommand hellowor¦` and the command syntax is `/mycommand <ChatArgument>`, the result of `input()` would be `"hellowor"`.

-----

```java
String fullInput();
```

`fullInput()` is the full input that the player has typed, including the leading `/` symbol which is required to start a command. If a user is typing `/mycommand hellowor¦`, the result of `fullInput()` would be `"/mycommand hellowor"`.

-----

```java
T parsedInput();
```

`parsedInput()` is similar to `input()`, except it has been parsed by the CommandAPI's argument parser. This is a representation of what the argument in the executor would look like. For a `ChatArgument` the return type is `BaseComponent[]`, and for `AdventureChatArgument` the return type is `Component`.

-----

## Using the chat preview function as the argument's value

The `ChatArgument` and `AdventureChatArgument` classes also include a method, `usePreview`:

```java
public T usePreview(boolean usePreview);
```

The `usePreview(boolean usePreview)` method lets you specify whether you would like the previewing function to be used as the argument's value during execution. If set to `true`, when the command's `.executes()` method is called, the argument value (e.g. `arg[0]`) will be the same as the content generated by the function provided to `withPreview()`.

-----

## Chat preview examples

<div class="example">

### Example - Using chat preview

Say we wanted to make our own `/broadcast` command that allowed the user to use `&` chat colors. We can use chat preview to show users what the result of their `/broadcast` command would look like before running the command. We'll use the following command syntax:

```mccmd
/broadcast <message>
```

Because the `ChatArgument` and `AdventureChatArgument` can support entity selectors (such as `@p`), it's best to use the `info.parsedInput()` method to handle parsed entity selectors. In our code, we use the `.withPreview()` method and take the parsed input and convert it to plain text. We then convert the plain text with `&` characters into component text to be displayed to the user.

For execution, we do the same procedure, because the text that the user enters still has `&` characters that need to be converted into a component.

<div class="multi-pre">

```java,Spigot_(Java)
{{#include ../../commandapi-documentation-code/src/main/java/dev/jorel/commandapi/examples/java/Examples.java:chatPreview1}}
```

```java,Paper_(Java)
{{#include ../../commandapi-documentation-code/src/main/java/dev/jorel/commandapi/examples/java/Examples.java:chatPreview2}}
```

```kotlin,Spigot_(Kotlin)
{{#include ../../commandapi-documentation-code/src/main/kotlin/dev/jorel/commandapi/examples/kotlin/Examples.kt:chatPreview1}}
```

```kotlin,Paper_(Kotlin)
{{#include ../../commandapi-documentation-code/src/main/kotlin/dev/jorel/commandapi/examples/kotlin/Examples.kt:chatPreview2}}
```

</div>

</div>

<div class="example">

### Example - Using chat preview with `usePreview()`

Extending on the example above where we created a `/broadcast` command with chat preview support, we can simplify the code by using `.usePreview(true)` to use the preview function as the value of our argument in our executor function. We'll use the same command syntax as the previous example:

```mccmd
/broadcast <message>
```

By using `.usePreview(true)`, we don't have to re-translate `&` formatting codes into their corresponding components because that has already been done by the preview function specified in `.withPreview()` method.

<div class="multi-pre">

```java,Spigot_(Java)
{{#include ../../commandapi-documentation-code/src/main/java/dev/jorel/commandapi/examples/java/Examples.java:chatPreview3}}
```

```java,Paper_(Java)
{{#include ../../commandapi-documentation-code/src/main/java/dev/jorel/commandapi/examples/java/Examples.java:chatPreview4}}
```

```kotlin,Spigot_(Kotlin)
{{#include ../../commandapi-documentation-code/src/main/kotlin/dev/jorel/commandapi/examples/kotlin/Examples.kt:chatPreview3}}
```

```kotlin,Paper_(Kotlin)
{{#include ../../commandapi-documentation-code/src/main/kotlin/dev/jorel/commandapi/examples/kotlin/Examples.kt:chatPreview4}}
```

</div>

</div>