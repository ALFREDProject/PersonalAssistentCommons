package eu.alfred.api.proxies.interfaces;

/**
 * Created by Robert on 22.09.2015.
 */
public interface ICadeCommand {
    void performAction(String command, java.util.Map<String, String> parameters);
}
