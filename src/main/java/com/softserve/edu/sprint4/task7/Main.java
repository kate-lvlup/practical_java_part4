package custom;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@interface JsonElement {
    String key() default "";
}


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@interface JsonSerializable {
}

class JsonSerializationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public JsonSerializationException(String message) {
        super(message);
    }
}

public class Main {
    public static void main(String[] args) {
        String string = new String();
        ObjectToJsonConverter serializer = new ObjectToJsonConverter();

        try {
            serializer.convertToJson(string);
        } catch (JsonSerializationException var5) {
            JsonSerializationException e = var5;
            System.out.println(e);
        }

        User user = new User("UserName", "UserLastName", 34);
        String jsonString = serializer.convertToJson(user);
        System.out.println("{\"personAge\":\"34\",\"firstName\":\"UserName\",\"lastName\":\"UserLastName\"}".equals(jsonString));
    }
}

class ObjectToJsonConverter {
    ObjectToJsonConverter() {
    }

    public String convertToJson(Object object) throws JsonSerializationException {
        try {
            this.checkIfSerializable(object);
            return this.getJsonString(object);
        } catch (Exception var3) {
            Exception e = var3;
            throw new JsonSerializationException(e.getMessage());
        }
    }

    private void checkIfSerializable(Object object) {
        if (Objects.isNull(object)) {
            throw new JsonSerializationException("Can't serialize a null object");
        } else {
            Class<?> clazz = object.getClass();
            if (!clazz.isAnnotationPresent(JsonSerializable.class)) {
                throw new JsonSerializationException("The class " + clazz.getSimpleName() + "is not annotated with JsonSerializable");
            }
        }
    }

    private String getJsonString(Object object) throws IllegalArgumentException, IllegalAccessException {
        Class<?> clazz = object.getClass();
        Map<String, String> jsonElementMap = new HashMap<>();
        Field[] var4 = clazz.getDeclaredFields();
        int var5 = var4.length;

        for (int var6 = 0; var6 < var5; ++var6) {
            Field field = var4[var6];
            field.setAccessible(true);
            if (field.isAnnotationPresent(JsonElement.class)) {
                jsonElementMap.put(this.getKey(field), field.get(object).toString());
            }
        }

        String jsonString = (String) jsonElementMap.entrySet().stream().map((entry) -> {
            String var10000 = (String) entry.getKey();
            return "\"" + var10000 + "\":\"" + (String) entry.getValue() + "\"";
        }).collect(Collectors.joining(","));
        return "{" + jsonString + "}";
    }

    private String getKey(Field field) {
        String value = ((JsonElement) field.getAnnotation(JsonElement.class)).key();
        return value.isEmpty() ? field.getName() : value;
    }
}

@JsonSerializable
class User {
    @JsonElement
    private String firstName;
    @JsonElement
    private String lastName;
    @JsonElement(
            key = "personAge"
    )
    private int age;
    private String password;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
