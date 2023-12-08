package amber;

import amber.day1.Day1Solver;

import java.net.URL;

public abstract class Solver {
    public abstract void solve();
    protected static URL getURL(String resourceName) {
        URL resourceURL = Solver.class.getClassLoader().getResource(resourceName);
        if (resourceURL == null) {
            throw new RuntimeException("Could not find resource " + resourceName);
        }
        return resourceURL;
    }
}
