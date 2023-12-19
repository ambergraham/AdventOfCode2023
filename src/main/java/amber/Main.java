package amber;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Reflections reflections = new Reflections("amber");
        Set<Class<? extends Solver>> solvers = reflections.getSubTypesOf(Solver.class);
        for (Class<? extends Solver> solver : solvers) {
            try {
                solver.getConstructor().newInstance().solve();
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }
}