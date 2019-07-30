package cs518.samples.intentdata2

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView

/**
 * App used to send data to child activity, then receive data from child
 * activity
 *
 * @author tricia
 */
class MainActivity : Activity() {
    internal var et: EditText? = null
    internal var tv2: TextView? = null
    internal var tv3: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et = findViewById<View>(R.id.data1) as EditText
        tv2 = findViewById<View>(R.id.tv2) as TextView
        tv3 = findViewById<View>(R.id.tv3) as TextView
    }

    fun launchActivity2(view: View) {
        val i = Intent(this, Activity2.kt)

        logIt(et.text.toString())
        i.putExtra("DATA1", et.text.toString())

        // request code = 0, only one activity used
        startActivityForResult(i, 0)

    }

    /**
     * Lifecycle method called when an activity you launched exits
     *
     * @param request
     * int originally supplied to startActivityForResult()
     * @param result
     * int returned by the child activity through its setResult().
     * @param i
     * Intent can be used to return (extras) result data to caller
     */
    override fun onActivityResult(request: Int, result: Int, i: Intent?) {
        super.onActivityResult(request, result, i)
        val data: String?
        when (result) {
            Activity.RESULT_OK -> {
                logIt("result ok")
                if (i != null && i.hasExtra("DATA2")) {
                    data = i.extras!!.getString("DATA2")
                    Log.d("DATA", data!!)
                    if (data == null)
                        tv3.setText(R.string.nodata)
                    else
                        tv3.text = data
                } else {
                    tv3.text = "No intent or no extras"
                }
                tv3.setTextColor(Color.MAGENTA)
            }
            Activity.RESULT_CANCELED -> {
                logIt("result canceled")
                tv3.text = "Cancel returned from child Activity"
                tv3.setTextColor(Color.RED)
            }
            else -> {
                logIt("result canceled")
                tv3.text = "Cancel returned from child Activity"
                tv3.setTextColor(Color.RED)
            }
        }
        tv2.visibility = View.VISIBLE
        tv3.visibility = View.VISIBLE
    } // onActivityResult()

    companion object {

        /**
         * Simple wrapper method for Log.d()
         *
         * @param msg
         * string to be logged
         */
        fun logIt(msg: String) {
            val TAG = "INTDATA1"
            Log.d(TAG, msg)
        }
    }
}