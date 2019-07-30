package cs518.samples.intentdata3

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

/**
 *
 * This class does two things 1 receive data from the Intent of the Activity
 * that started it. 2 create a new Intent to send data back to the Activity that
 * started it.
 *
 * It displays the received data on the UI.
 *
 * @author Tricia
 */
class Activity2 : Activity() {
    internal var returnHit = false
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2_main)

        val value = intent.extras!!.getString("DATA1")
        val tv2 = findViewById<View>(R.id.a2tv2) as TextView

        if (value == null || value.isEmpty()) {
            tv2.setText(R.string.nodata)
            tv2.setTextColor(Color.RED)
        } else {
            MainActivity.logIt("DATA1 $value")
            tv2.text = value
            tv2.setTextColor(Color.MAGENTA)
        }
    } // onCreate()

    fun returnData(view: View) {
        val et1 = findViewById<View>(R.id.a2et1) as EditText
        val i = Intent()
        i.putExtra("DATA2", et1.text.toString())
        setResult(Activity.RESULT_OK, i)
        returnHit = true
        finish()
    }

    override fun finish() {
        if (!returnHit) {
            val i = Intent()
            i.putExtra("DATA2", "Data dropped, back button was hit!")
            setResult(Activity.RESULT_CANCELED, i)
        }
        super.finish()
    }

}  // Activity2
