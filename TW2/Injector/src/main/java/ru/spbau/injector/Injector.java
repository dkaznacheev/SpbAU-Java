package ru.spbau.injector;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Class implementing dependency injection.
 */
public class Injector {

    /**
     * Set of classes that were given to initialize method
     */
    private static List<Class<?>> classes = new ArrayList<>();

    /**
     * List of objects that are currently getting instantiated.
     * We can't call for a class that's in here, that would be an injection cycle.
     */
    private static Set<Class<?>> toInst = new HashSet<>();

    /**
     * Map of objects that already have an instance.
     * When calling for class that's in this map, we return its instance.
     */
    private static Map<Class<?>, Object> instantiated = new HashMap<>();

    /**
     * Public method that returns an instance of a class in context of available class implementations.
     * @param rootClassName name of the class to instantiate
     * @param implementationClassNames list of available classes' names
     * @return instance Object of this class
     */
    public static Object initialize(String rootClassName, List<String> implementationClassNames) throws
            ClassNotFoundException,
            IllegalAccessException,
            AmbiguousImplementationException,
            ImplementationNotFoundException,
            InstantiationException,
            InjectionCycleException,
            InvocationTargetException {

        Class<?> rootClass = Class.forName(rootClassName);
        for (String name : implementationClassNames) {
            classes.add(Class.forName(name));
        }
        if (!classes.contains(rootClass)) {
            classes.add(rootClass);
        }

        return instantiate(rootClass);
    }


    /**
     * Creates an instance of given class (recursively), adds it to map of class-objects,
     * and returns the instance.
     * @param clazz class to instantiate
     * @return instance
     */
    private static Object instantiate(Class<?> clazz) throws
            AmbiguousImplementationException,
            InjectionCycleException,
            ImplementationNotFoundException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException {

        if (instantiated.containsKey(clazz)) {
            return instantiated.get(clazz);
        }
        toInst.add(clazz);

        Constructor constructor = clazz.getConstructors()[0];
        Class<?>[] parameterClasses = constructor.getParameterTypes();
        Object[] parameterObjects = new Object[parameterClasses.length];

        for (int i = 0; i < parameterClasses.length; i++) {
            Class<?> parameter = parameterClasses[i];
            Class<?> fittingClass = null;
            for (Class<?> declaredClass : classes) {
                if (parameter.isAssignableFrom(declaredClass)) {
                    if (fittingClass != null) {
                        throw new AmbiguousImplementationException();
                    }
                    fittingClass = parameter;
                }
            }
            if (fittingClass == null) {
                throw new ImplementationNotFoundException();
            }
            if (toInst.contains(fittingClass)) {
                throw new InjectionCycleException();
            }
            parameterObjects[i] = instantiate(fittingClass);
        }
        Object instance = constructor.newInstance(parameterObjects);
        instantiated.put(clazz, instance);
        toInst.remove(clazz);
        return instance;
    }
}
