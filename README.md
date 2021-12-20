# Room KSP bug (minimal verifiable example)

Using:

- Room 2.4.0
- Kotlin 1.5.31
- KSP 1.5.31-1.0.1

To reproduce the bug:

1. Note that the `Awesomeness` enum is missing from [User.kt](app/src/main/java/com/example/roomkspbug/User.kt)
2. Note that [AppDatabase.kt](app/src/main/java/com/example/roomkspbug/AppDatabase.kt) has two converters - one for `Instant`, and one for `Awesomeness`
3. Run [InsertUserIntoDatabaseTest](app/src/test/java/com/example/roomkspbug/InsertUserIntoDatabaseTest.kt) and see that it fails with:
    - `java.time.format.DateTimeParseException: Text 'AWESOME' could not be parsed at index 0`
    - This means that the string `AWESOME` has been written to the `born_at` field! ⚡️🤯
4. The reason this happens is that the generated code in [UserDao_Impl.java](app/build/generated/ksp/debug/java/com/example/roomkspbug/UserDao_Impl.java) contains these lines in the generated `bind` method:

```java
        final String _tmp = __timeConverter.instantToValue(value.getBornAt());      // this is expected
        final Awesomeness _tmp_1 = __awesomenessConverter.valueToAwesomeness(_tmp); // ⚡️ fail - converting the String to Awesomeness
        final String _tmp_2 = __awesomenessConverter.awesomenessToValue(_tmp_1);
```
