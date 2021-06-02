import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PluginLoader {
    private final Map<String, PluginInfo> plugins = new HashMap<>();

    public void addPluginByName(File pluginFile) throws Exception {
        String fileName = pluginFile.getName();
        if (plugins.containsKey(fileName)) {
            throw new Exception(fileName + " is already loaded");
        }

        PluginInfo pluginInfo = new PluginInfo(pluginFile);
        plugins.put(fileName, pluginInfo);
    }

    public void removePluginByName(String jarName) throws Exception {
        if (!plugins.containsKey(jarName)) {
            throw new Exception(jarName + " is not loaded");
        }

        plugins.remove(jarName);
    }
}
