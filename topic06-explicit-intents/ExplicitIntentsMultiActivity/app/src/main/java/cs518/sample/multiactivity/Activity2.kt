package cs518.sample.multiactivity

import android.app.Activity
import android.os.Bundle

/*
 * This Activity is total independent of any others that might fire it
 * no data is received or returned
 */
class Activity2 : Activity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2)
    }

}