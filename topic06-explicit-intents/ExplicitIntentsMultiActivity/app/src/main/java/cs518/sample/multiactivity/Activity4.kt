package cs518.sample.multiactivity

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.content.Intent

/*
 * This class receives data returns data and it returns a result
 */
class Activity4 : Activity() {
    private var counter = 0
    private var tv2: TextView? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity4)
        // get the data from the Activity that fired this intent
        val value1 = intent.extras!!.getString(Constants.KEY3)
        val tv1 = findViewById(R.id.datatv1) as TextView
        tv2 = findViewById(R.id.datatv2) as TextView
        if (value1 != null) {
            Activity1.logIt(value1)
            tv1.text = value1
        } else {
            tv1.text = "no data received from invoking activity "
        }

    } // onCreate

    fun countNow(v: View) {
        counter++
        tv2!!.text = Integer.toString(counter)
    }

    /*
 * Note this onPause() sets a result of ok
 * however since we rely on the back button to return to the caller
 * whenever the back button is hit is sends a cancel, note the data is ok
 * but the result is cancel
 *
 * (non-Javadoc)
 * @see android.app.Activity#onPause()
 * 	public void onPause() {
		// set up a new Intent
		Intent i = new Intent();
		i.putExtra(Constants.KEY4, counter);
		setResult(RESULT_OK, i);
		super.onPause();
	}

 */
    override fun finish() {
        // set up a new Intent
        val i = Intent()
        i.putExtra(Constants.KEY4, counter)
        setResult(Activity.RESULT_OK, i)
        super.finish()
    }
} // class