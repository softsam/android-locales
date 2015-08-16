package com.orange.androidlocales;

import android.content.res.Configuration;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Wrapper that grants access to the SDK ActivityManagerNative, to be able to change the system locale.
 * Access to this class are highly restricted since these operations may be considered as harmful.
 * Access to this class requires introspection, and this is why all the methods throw a ReflectiveOperationException exception.
 */
public class ActivityManagerNative {

    public static final String ACTIVITY_MANAGER_NATIVE_CLASS_NAME = "android.app.ActivityManagerNative";
    private Object instance;
    private Class clazz;

    public ActivityManagerNative() throws ReflectiveOperationException {
        clazz = Class.forName(ACTIVITY_MANAGER_NATIVE_CLASS_NAME);
        Method methodGetDefault = clazz.getMethod("getDefault");
        methodGetDefault.setAccessible(true);
        instance = methodGetDefault.invoke(clazz);
    }

    /**
     * Get the current system configuration. The locale of this instance can be modified and will be saved.
     *
     * @return The current system configuration.
     * @throws ReflectiveOperationException Thrown if the configuration could not be retrieved.
     */
    public Configuration getConfiguration() throws ReflectiveOperationException {
        // Get configuration from instance
        Method getConfiguration = clazz.getMethod("getConfiguration");
        getConfiguration.setAccessible(true);
        Configuration config = (Configuration) getConfiguration.invoke(instance);

        // Allow the locale to be saved when changed
        Field field = config.getClass().getField("userSetLocale");
        field.setBoolean(config, true);

        return config;
    }

    /**
     * Update the current system configuration to the given one. Use with caution, and prefer to set a configuration from the current system configuration, using the getConfiguration method.
     *
     * @param configuration The new system configuration.
     */
    public void updateConfiguration(Configuration configuration) throws ReflectiveOperationException {
        // amn.updateConfiguration(config);
        Method updateConfiguration = clazz.getMethod("updateConfiguration", Configuration.class);
        updateConfiguration.setAccessible(true);
        updateConfiguration.invoke(instance, configuration);
    }
}
