package cs518.sample.multiactivity

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

/*
 * The sole reason for this class is to receive data from the Intent of the Activity that started it
 * It puts the data on the UI
 */
class Activity5 : Activity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity5)
        val value1 = intent.extras!!.getString(Constants.KEY1)
        val tv1 = findViewById(R.id.textView1) as TextView
        if (value1 != null) {
            Activity1.logIt("key1 data: $value1")
            tv1.text = "key1 data: $value1"
        } else {
            tv1.text = "key1 data: none received"
        }
        val value2 = intent.extras!!.getString(Constants.KEY2)
        val tv2 = findViewById(R.id.textView2) as TextView
        if (value2 != null) {
            Activity1.logIt("key2 data: $value2")
            tv2.text = "key2 data: $value2"
        } else {
            tv2.text = "key2 data: none received"
        }
    } // onCreate()

}