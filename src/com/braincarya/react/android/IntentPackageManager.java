package com.braincarya.react.android;

import com.facebook.react.ReactPackage;



import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;


public class IntentPackageManager implements ReactPackage {
    public static class IntentModule extends ReactContextBaseJavaModule{
        private Map<String,Object> extras = new HashMap<>();

        //Module name - you can accesc from js to variables using this name
        // e.g. NativeModules.%intentName%
        private final String intentName;

        IntentModule(ReactApplicationContext reactContext, Map<String, Object> values,
                     String intentName) {
            super(reactContext);
            this.extras = values;
            this.intentName = intentName;
        }

        @Override
        public String getName() {
            return intentName;
        }

        @Nullable
        @Override
        public Map<String, Object> getConstants() {
            return this.extras;
        }
    }

    private Map<String,Object> extras;
    private String intentName;

    public IntentPackageManager(Map<String,Object> extras, String intentName) {
        this.extras = extras;
        this.intentName = intentName;
    }


    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();


        modules.add(new IntentModule(reactContext, extras, intentName));

        return modules;
    }

    @Override
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
}
