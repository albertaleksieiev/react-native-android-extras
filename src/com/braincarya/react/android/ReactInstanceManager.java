package com.braincarya.react.android;

import java.util.HashMap;

/**
 * Add new ability to com.facebook.react.ReactInstanceManager
 *  send extra data to JS by using this class and method `builder`
 *
 *  You just need pass extras in setExtras method,
 *  import module to JS `import { NativeModules } from 'react-native'`,
 *  and call NativeModules.ReactIntentExtras.key - where key is object from extras(setExtras method).
 */
public abstract class ReactInstanceManager extends com.facebook.react.ReactInstanceManager {
    public static class ExtrasBuilder extends com.facebook.react.ReactInstanceManager.Builder{
        private HashMap<String, Object> extras = new HashMap<>();


        private String name = "ReactIntentExtras";

        /**
         * @param name  You can access from js to variables using this %name%
         *              e.g. NativeModules.%name%
         * @return Builder instance
         */
        public Builder setIntentName(String name){
            this.name = name;
            return this;
        }

        /**
         * Set extras which you can receive in js,
         * @param extras NativeModules.%name%.param
         * @return Builder instance
         */
        public Builder setExtras(HashMap<String,Object> extras){
            this.extras = extras;
            return this;
        }


        @Override
        public com.facebook.react.ReactInstanceManager build() {
            this.addPackage(new IntentPackageManager(extras, this.name));
            return super.build();
        }
    }


    public static ExtrasBuilder builder() {
        return new ReactInstanceManager.ExtrasBuilder();
    }
}
