package ttanja;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import ttanja.people.User;
import ttanja.vehicle.Car;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;


public class Program {
    static HashMap<String, Class>  classesMap = new HashMap<>();
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        Reflections reflections = new Reflections("ttanja", new SubTypesScanner(false));
        Set<Class> classes = new HashSet<Class>(reflections.getSubTypesOf(Object.class));
        classes.forEach(it -> classesMap.put(
                it.getName().substring(it.getName().lastIndexOf('.') + 1), it));

        printClassesNames(classesMap);

        System.out.print("Enter class name:\n-> ");
        Class myClass = getClassByName(reader.readLine());
        printFieldsByClass(myClass);

        Object created = createAnObject(myClass);

        changeField(created);

        callMethod(created);
        
        reader.close();
    }

    private static void callMethod(Object created) throws IOException{
        System.out.print("Enter name of the method for call:\n-> ");
        try{
            Method[] methods = created.getClass().getDeclaredMethods();
            String nameMethod = reader.readLine();
            Method method = null;
            for (Method it: methods){
                String tmp = it.toString().substring(it.toString().lastIndexOf('.') + 1);
                    if (tmp.equals(nameMethod))
                method = it;
            }
            method.setAccessible(true);
            int i = method.getParameterCount();
            List<Parameter> parameters = Arrays.stream(method.getParameters()).collect(Collectors.toList());
            List<Object> arguments = new ArrayList<>();
                    parameters.forEach(it -> {
                        try {
                            System.out.printf("Enter %s value:\n-> ", it.getType().getSimpleName());
                            arguments.add(scannerGetType(it));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    Object ret = method.invoke(created, arguments.toArray());
            System.out.printf("Method returned:\n%s\n", ret);
        }catch (IOException |
                InvocationTargetException |
                IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static void changeField(Object created) {
        System.out.print("Enter name of the field for changing:\n-> ");
        try {
            Field fieldToChange = created.getClass().getDeclaredField(reader.readLine());
            System.out.printf("Enter %s value:\n-> ", fieldToChange.getType().getSimpleName());
            fieldToChange.setAccessible(true);
            fieldToChange.set(created, scannerGetType(fieldToChange));
            System.out.printf("Object created: %s\n", created);
            System.out.println("---------------------");
        }catch (IOException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static void printFieldsByClass(Class myClass) {
        System.out.println("fields:");
        Arrays.stream(myClass.getDeclaredFields()).forEach(it -> System.out.println(
                "        " + it.getType().toString().substring(
                        it.getType().toString().lastIndexOf('.') + 1) + " " +
                        it.toString().substring(it.toString().lastIndexOf('.') + 1)));


        System.out.println("methods:");
        Arrays.stream(myClass.getDeclaredMethods()).filter(
                it -> !it.toString().substring(it.toString().lastIndexOf('.') + 1).equals("toString()"))
                .forEach(it -> System.out.printf("          %s%s\n",
                        it.getReturnType() == null ? "" : (it.getReturnType() + " "),
                        it.toString().substring(it.toString().lastIndexOf('.') + 1)));
        System.out.println("---------------------");
    }

    private static Class getClassByName(String className) throws ClassNotFoundException {
        Class myClass = classesMap.get(className);
        if (null == myClass)
            System.err.printf("Class with name %s not found", className);
        System.out.println("---------------------");
        return myClass;
    }

    public static void printClassesNames(Map<String, Class> classes){
        System.out.println("Classes:");
        for (Map.Entry<String, Class> map: classes.entrySet()){
            if (map.getKey().equals("Program"))
                continue;
            System.out.println("  - " + map.getKey());
        }
        System.out.println("---------------------");
    }

    public static Object createAnObject(Class toCreateType) throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Object created = null;
        List<Field> fields = Arrays.asList(toCreateType.getDeclaredFields());
        System.out.println("Let’s create an object.");
        Constructor<?>[] constructors = toCreateType.getDeclaredConstructors();
        Parameter[] parameters = null;
        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameterCount() > 0) {
                parameters = constructor.getParameters();
                break;
            }
        }
        List<Object> arguments = new ArrayList<>();
        assert parameters != null;
        for (int i = 0; i < fields.size(); i++){
            System.out.printf("%s:\n-> ", fields.get(i).getName());
                    arguments.add(scannerGetType(parameters[i]));
        }
        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameterCount() > 0) {
                created = Arrays.stream(toCreateType.getDeclaredConstructors())
                        .filter(it -> it.getParameterCount() > 0)
                        .findAny()
                        .get().newInstance(arguments.toArray());
                break;
            }
        }
        System.out.printf("Object created: %s\n", created);
        System.out.println("---------------------");
        return created;
    }
    private static Object scannerGetType(Parameter param) throws IOException {
        switch (param.getType().getSimpleName().toLowerCase()) {
            case "string":
                return reader.readLine();
            case "int":
            case "integer":
                return Integer.parseInt(reader.readLine());
            case "long":
                return Long.parseLong(reader.readLine());
            case "double":
                return Double.parseDouble(reader.readLine());
            case "float":
                return Float.parseFloat(reader.readLine());
            case "char":
            case "character":
                return reader.readLine().charAt(0);
            case "user":
                reader.readLine();
                return null;
            default:
                throw new RuntimeException("Unrecognized type");
        }
    }

    private static Object scannerGetType(Field field) throws IOException {
        switch (field.getType().getSimpleName().toLowerCase()) {
            case "string":
                return reader.readLine();
            case "int":
            case "integer":
                return Integer.parseInt(reader.readLine());
            case "long":
                return Long.parseLong(reader.readLine());
            case "double":
                return Double.parseDouble(reader.readLine());
            case "float":
                return Float.parseFloat(reader.readLine());
            case "char":
            case "character":
                return reader.readLine().charAt(0);
            case "user":
                reader.readLine();
                return null;
            default:
                throw new RuntimeException("Unrecognized type");
        }
    }
}
