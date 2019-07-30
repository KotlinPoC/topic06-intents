package cs518.samples.intentdata3

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
    internal val REQ_ACTIVITY2 = 1
    internal val REQ_ACTIVITY3 = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et = findViewById<View>(R.id.data1) as EditText
        tv2 = findViewById<View>(R.id.tv2) as TextView
        tv3 = findViewById<View>(R.id.tv3) as TextView
    }

    /**
     * onClick method set in layout
     *
     * @param view
     */

    fun launchActivity2(view: View) {
        val i = Intent(this, Activity2::clas.java)

        logIt(et.text.toString())
        i.putExtra("DATA1", et.text.toString())

        // request code = 0, only one activity used
        startActivityForResult(i, REQ_ACTIVITY2)

    }

    /**
     * onClick method set in layout
     *
     * @param view
     */

    fun launchActivity3(view: View) {
        val i = Intent(this, Activity3::class.java)

        logIt(et.text.toString())
        i.putExtra("DATA1", et.text.toString())

        // request code = 0, only one activity used
        startActivityForResult(i, REQ_ACTIVITY3)

    }

    /**
     * Lifecycle method called when an activity you launched exits
     *
     * @param request
     * int originally supplied to startActivityForResult() allows us
     * to determine which activity this result is returned from
     * @param result
     * int returned by the child activity through its setResult().
     * @param i
     * Intent can be used to return (extras) result data to caller
     */
    override fun onActivityResult(request: Int, result: Int, i: Intent) {
        super.onActivityResult(request, result, i)
        when (result) {
            Activity.RESULT_OK -> {
                logIt("result ok")
                setDataonUI(request, i)
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

    /**
     * method determines who sent the data back, in this case we have only two
     * possible activities and each of them return simple string data
     *
     * @param request
     * which activity was invoked
     * @param i
     * intent from the returned activity
     */
    internal fun setDataonUI(request: Int, i: Intent?) {
        val data: String?
        if (i == null) {
            tv3.setText(R.string.missingIntent)
            tv3.setTextColor(Color.RED)
        } else {
            when (request) {
                // Activity 2 returned
                REQ_ACTIVITY2 -> if (i.hasExtra("DATA2")) {
                    data = i.extras!!.getString("DATA2")
                    Log.d("DATA", data!!)
                    if (data == null)
                        tv3.setText(R.string.nodata)
                    else
                        tv3.text = data
                } else {
                    tv3.setText(R.string.missingExtra)
                }
                // Activity 3 returned
                REQ_ACTIVITY3 -> if (i.hasExtra("DATA3")) {
                    data = i.extras!!.getString("DATA3")
                    Log.d("DATA", data!!)
                    if (data == null)
                        tv3.setText(R.string.nodata)
                    else
                        tv3.text = data
                } else {
                    tv3.setText(R.string.missingExtra)
                }
                else -> {
                    tv3.setText(R.string.unknownReq)
                    tv3.setTextColor(Color.MAGENTA)
                }
            }
        } // setDataonUI()
    } // MainActivity

    companion object {

        /**
         * Simple wrapper method for Log.d()
         *
         * @param msg
         * string to be logged
         */
        fun logIt(msg: String) {
            val TAG = "INTENTDATA"
            Log.d(TAG, msg)
        }
    }
}