package p20Annotations.p4Unit.Unit;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Handler {

    private Method createMethod;
    private Method cleanUpMethod;
    private List<Method> testMethods = new ArrayList<>();
    private Class<?> cl;

    public Handler(String className) throws Exception {
        cl = Class.forName(className);
        for (Method m : cl.getDeclaredMethods()) {
            if (m.getAnnotation(Test.class) != null) {
                testMethods.add(m);
            }
            if (m.getAnnotation(TestObjectCreate.class) != null) {
                createMethod = m;
            }
            if (m.getAnnotation(TestObjectCleanUp.class) != null) {
                cleanUpMethod = m;
            }
        }
    }

    private Object createTestObject() throws Exception {
        Object testObject;
        if (createMethod != null) {
            createMethod.setAccessible(true);
            testObject = createMethod.invoke(null);
        } else {
            Constructor<?> constructor = cl.getDeclaredConstructor();
            constructor.setAccessible(true);
            testObject = constructor.newInstance();
        }
        return testObject;
    }

    private void cleanUp(Object testObject) throws Exception {
        if (cleanUpMethod != null) {
            cleanUpMethod.setAccessible(true);
            cleanUpMethod.invoke(null, testObject);
        }
    }

    public void executeTests() throws Exception {
        for (Method m : testMethods) {
            Object testObject = createTestObject();
            m.setAccessible(true);
            System.out.print("Test " + cl.getName() + "::" + m.getName());
            if (m.getReturnType().equals(boolean.class)) {
                Boolean result = (Boolean) m.invoke(testObject);
                if (result) {
                    System.out.println(" passed");
                } else {
                    System.out.println(" failed");
                }
            } else try {
                m.invoke(testObject);
                System.out.println(" passed");
            } catch (InvocationTargetException e) {
                System.out.println(" failed");
                System.out.println("Reason " + e.getCause());
            }
            cleanUp(testObject);
        }
    }
}
