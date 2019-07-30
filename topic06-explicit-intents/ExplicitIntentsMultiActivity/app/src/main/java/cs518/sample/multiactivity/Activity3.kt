package cs518.sample.multiactivity

import android.app.Activity
import android.os.Bundle
import android.view.View

/*
 * This class receives no data but it returns a result, depending on which button is clicked
 */
class Activity3 : Activity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity3)
    }

    fun thisResult(v: View) {
        setResult(Activity.RESULT_OK)
        finish()
    } //thisResult()

    fun thatResult(v: View) {
        setResult(Activity.RESULT_CANCELED)
        finish()
    } //thisResult()

}