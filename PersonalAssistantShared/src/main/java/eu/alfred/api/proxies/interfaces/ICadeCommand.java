package eu.alfred.api.proxies.interfaces;

/**
 * Created by Robert on 22.09.2015.
 */
public interface ICadeCommand {

    void performAction(String command, java.util.Map<String, String> parameters);
    void performWhQuery(String command, java.util.Map<String, String> parameters);
    void performValidity(String command, java.util.Map<String, String> parameters);
    void performEntityRecognizer(String command, java.util.Map<String, String> parameters);
}
