import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginInfo {
    private static final String MAIN_CLASS_PROPERTY = "main.class";
    private static final String PROPERTIES = "settings.properties";

    private final XMLTransformerPlugin instance;

    public PluginInfo(File jarFile) throws Exception {
        try {
            Properties props = getPluginProps(jarFile);
            if (props == null) {
                throw new IllegalArgumentException("No props file found");
            }

            String pluginClassName = props.getProperty(MAIN_CLASS_PROPERTY);
            if (pluginClassName == null || pluginClassName.length() == 0) {
                throw new Exception("Missing property main.class");
            }

            URL jarURL = jarFile.toURI().toURL();
            URLClassLoader classLoader = new URLClassLoader(new URL[]{jarURL},
                    getClass().getClassLoader().getParent());
            Class<?> pluginClass = classLoader.loadClass(pluginClassName);
            instance = (XMLTransformerPlugin) pluginClass.newInstance();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public XMLTransformerPlugin getPluginInstance() {
        return instance;
    }

    private Properties getPluginProps(File file) throws IOException {
        Properties result = null;
        JarFile jar = new JarFile(file);
        Enumeration <JarEntry> entries = jar.entries();

        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            if (entry.getName().equals(PROPERTIES)) {
                try (InputStream is = jar.getInputStream(entry)) {
                    result = new Properties();
                    result.load(is);
                }
            }
        }
        return result;
    }
}