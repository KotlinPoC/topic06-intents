package cs518.sample.multiactivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import android.net.Uri
import android.util.Log

/**
 *
 * icon: elvis, holmes, monroe from http://iconka.com/persons/
 * icon: targetarrow from https://www.iconfinder.com/iconsets/5-o-clock-shades-icon-set-2#readme
 * @author Tricia
 */
class Activity1 : Activity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity1)
    }

    // use explicit intent -> name the target Activity class
    // send no data, receive no data back
    fun handleButton1Click(view: View) {
        startActivity(Intent(this, Activity2::class.java))
    }


    // use implicit intent -> (action, data.for.action)
    /*
	 * Implicit intent is send to the Android system, it searches for all
	 * components which are registered for the specific action and the fitting
	 * data type. (Registered means AndroidManifest -> <intent-filter>
	 */
    fun handleButton2Click(view: View) {
        val url = "http://www.xkcd.org/"
        val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))

        /*
		 * can also add the url using Intent.setData()
		 * i.setData(Uri.parse(url)); can also add the action using
		 * Intent.setAction() i.setData(Intent.ACTION_VIEW);
		 */
        startActivity(i)
    }

    // use explicit intent -> name the target Activity class
    // send no data, expect a result back
    fun handleButton31Click(v: View) {
        val i: Intent
        i = Intent(this, Activity3::class.java)
        /*
		 * can also add the class using Intent.setClass() i.setClass(this,
		 * Activity3.class)
		 */
        /* int, requestCode = Constants.REQUEST1
		 * requestCode If >= 0, this code will be returned in onActivityResult() when the activity exits.
		 * */
        startActivityForResult(i, Constants.REQUEST1)

    }

    // use explicit intent -> name the target Activity class
    // send  data, expect a result back
    fun handleButton32Click(v: View) {
        val i: Intent
        i = Intent(this, Activity4::class.java)
        /*
		 * can also add the class using Intent.setClass() i.setClass(this,
		 * Activity3.class)
		 */
        i.putExtra(Constants.KEY3, "here's some data from main activity")
        startActivityForResult(i, Constants.REQUEST2)

    }

    fun handleButton4Click(view: View) {
        val i = Intent(this, Activity5::class.java)
        val et = findViewById(R.id.dataforactivity) as EditText
        logIt(et.text.toString())
        i.putExtra(Constants.KEY1, et.text.toString())
        i.putExtra(Constants.KEY2, "second piece of data for activity")
        startActivity(i)
    }

    fun handleButton5Click(view: View) {
        val i = Intent(this, Activity6::class.java)
        startActivityForResult(i, Constants.REQUEST3)
    }

    /*
	 *
	 * Called when result returned by an activity
	 *
	 * (non-Javadoc)
	 *
	 * @see android.app.Activity#onActivityResult(int, int,
	 * android.content.Intent)
	 *
	 * result:  RESULT_OK and RESULT_CANCELED in Activity class, set by returning
	 * activity
	 * request:  same one sent in startActivityForResult(Intent, request);
	 */
    override fun onActivityResult(request: Int, result: Int, data: Intent?) {
        super.onActivityResult(request, result, data)
        val counter: Int
        var msg = ("activity " + result + " returned from request: "
                + request)
        val tvresult = findViewById(R.id.result) as TextView
        val tv = findViewById(R.id.data) as TextView
        when (request) {
            // 1 sent to Activity 3 so result is from Activity 3
            Constants.REQUEST1 -> {

                if (result == Activity.RESULT_OK) {
                    logIt("$msg result ok")
                    toastIt("$msg result ok")
                }
                if (result == Activity.RESULT_CANCELED) {
                    logIt("$msg result canceled")
                    toastIt("$msg result canceled")
                }
                tvresult.text = msg
                tv.text = ""
            }
            // 2 sent to Activity 4 so result is from Activity 4
            Constants.REQUEST2 -> {

                if (data != null && data.hasExtra(Constants.KEY4)) {
                    counter = data.extras!!.getInt(Constants.KEY4)
                } else {
                    counter = 0
                }
                if (result == Activity.RESULT_OK) {
                    msg = msg + " key4 data count: " + Integer.toString(counter) + " result ok"
                    logIt(msg)
                    toastIt(msg)
                }
                if (result == Activity.RESULT_CANCELED) {
                    msg = msg + Integer.toString(counter) + " result canceled"
                    logIt(msg)
                    toastIt(msg)
                }
                tvresult.text = msg
                tv.text = ""
            }
            // 3 sent to Activity 6 so  result is from Activity 6
            Constants.REQUEST3 -> {
                tvresult.text = msg
                if (result == Activity.RESULT_OK) {

                    if (data!!.hasExtra(Constants.RETURN_KEY1)) {
                        msg = "key1 data: " + data.extras!!.getString(Constants.RETURN_KEY1)!!
                        logIt(msg)
                    }
                    if (data.hasExtra(Constants.RETURN_KEY2)) {
                        msg += " key2 data: " + data.extras!!.getDouble(Constants.RETURN_KEY2)
                        logIt(msg)
                    }
                    tv.text = msg
                } else {
                    logIt("Error, invalid return from Activity")
                }
            }
            else -> {
                logIt("no valid request received $msg")
                toastIt("no valid request received $msg")
                tvresult.text = msg
            }
        }
    }// onActivityResult

    fun toastIt(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    companion object {


        fun logIt(msg: String) {
            val TAG = "MULTACT"
            Log.d(TAG, msg)
        }
    }
}
