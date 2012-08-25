package com.aquasheep.Static;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;

public class MainActivity extends AndroidApplication {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /** Whether to use openGL ES 2.0 */
        boolean useOpenGLES20 = false;
        
        /** Start the actual game */
        initialize(new StaticGame(), useOpenGLES20);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }
}
