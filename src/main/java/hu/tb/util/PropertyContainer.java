package hu.tb.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Tivadar Bocz on 2016.11.03..
 */
@Component
public class PropertyContainer {

    private static String serverContextPath;

    @Autowired
    public PropertyContainer(@Value( "${server.context-path}" ) String serverContextPath) {
       this.serverContextPath = serverContextPath;
    }

    public static String getServerContextPath(){
        return serverContextPath;
    }
}
