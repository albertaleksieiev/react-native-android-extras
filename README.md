# React Native Android Extras

### Problem
You cannot pass extras from Android to React Native JS module, by using only builder.
But IOS have this ability, https://facebook.github.io/react-native/docs/communication-ios.html.

### Usage
JavaScript
```javascript
import { NativeModules } from 'react-native'
import {
    Text,
    View,
} from 'react-native';
class HelloWorld extends React.Component{
    render(){
         var  { page_name } = NativeModules.ReactIntentExtras;
        //TODO Initialize store or something with extras
       
        return (
            <Text>
                {page_name}
            </Text>
        );
    }
}
AppRegistry.registerComponent('HelloWorld', () => HelloWorld);
```

Java
```Java
//https://facebook.github.io/react-native/docs/integration-with-existing-apps.html
public class MyReactActivity extends Activity implements DefaultHardwareBackBtnHandler {
    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //Init extras
        HashMap<String,Object> extras = new HashMap<>();
        extras.put("page_name","Football team");

        mReactRootView = new ReactRootView(this);
        mReactInstanceManager = com.braincarya.react.android.ReactInstanceManager.builder()
                .setExtras(extras)
                .setApplication(getApplication())
                .setBundleAssetName("index.android.bundle")
                .setJSMainModuleName("index.android")
                .addPackage(new MainReactPackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();
        mReactRootView.startReactApplication(mReactInstanceManager, "HelloWorld", null);

        setContentView(mReactRootView);
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }
    
    //TODO initialize all methods
}
```
