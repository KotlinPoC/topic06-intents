package cs518.sample.implicitintents

import android.net.Uri
import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.provider.AlarmClock
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast

/**
 * Sample code for Implicit Intents
 * Implicit means the OS uses the URI to determine the
 * activity that is launched.
 * for other intents and other options with the intents used here
 * see https://developer.android.com/guide/components/intents-common
 *
 * @author Tricia
 */
class MainActivity : Activity() {
    private var spinner: Spinner? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinner = findViewById<View>(R.id.spinner) as Spinner
        val adapter = ArrayAdapter.createFromResource(this,
                R.array.intents, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.adapter = adapter
    }

    fun intentSelected(view: View) {
        val position = spinner!!.selectedItemPosition
        var intent: Intent? = null
        when (position) {
            0 -> {
                Log.d(TAG, "action view + data http uri")
                intent = Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.smbc-comics.com/"))
            }
            1 -> {
                Log.d(TAG, "action call + data tel uri")
                // number entered and dialer started
                intent = Intent(Intent.ACTION_CALL,
                        Uri.parse("tel:(514)123-4567"))
            }
            2 -> {
                Log.d(TAG, "action dial + data tel uri")
                // number entered, waits for user to send
                intent = Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:(514)123-4567"))
            }
            3 -> {
                Log.d(TAG, "action view + data geo uri")
                // won't work on avd without installing some software
                // test on real device
                // geo:latitude, longitude, altitude;u=uncertainty
                intent = Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:45.4897,-73.5878?z=10"))
            }
            4 -> {
                Log.d(TAG, "action view + data geo uri")
                intent = Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q=Paris"))
            }
            5 -> {
                Log.d(TAG, "action media image cap")
                intent = Intent("android.media.action.IMAGE_CAPTURE")
            }
            6 -> {
                Log.d(TAG, "action view + data content uri")
                intent = Intent(Intent.ACTION_VIEW,
                        Uri.parse("content://contacts/people/"))
            }
            7 -> {
                Log.d(TAG, "action view + content geo uri")
                intent = Intent(Intent.ACTION_EDIT,
                        Uri.parse("content://contacts/people/1"))
            }
            8 -> {
                Log.d(TAG, "action alarm set timer + special data")
                intent = Intent(AlarmClock.ACTION_SET_TIMER)
                        .putExtra(AlarmClock.EXTRA_MESSAGE, "Power Nap timer")
                        .putExtra(AlarmClock.EXTRA_LENGTH, 900)
                        .putExtra(AlarmClock.EXTRA_SKIP_UI, true)
            }
        }

        if (intent!!.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, R.string.activity_error,
                    Toast.LENGTH_LONG).show()
        }

    }

    companion object {
        val TAG = "IMPLICIT"
    }
}