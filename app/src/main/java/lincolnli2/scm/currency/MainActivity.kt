package lincolnli2.scm.currency

import android.app.Application
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import io.paperdb.Paper
import org.jetbrains.anko.toast
import org.json.JSONObject
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    companion object {
        val INPUT_KEY = "INPUT_KEY"
    }

    var clickCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == R.id.about_button)
        {
            //select about, display about activity
            val intent = Intent(this, AboutActivity::class.java)
            val fromEditText = findViewById<EditText>(R.id.from_input)
            intent.putExtra(INPUT_KEY,fromEditText.text.toString())
            startActivity(intent)
        }else if(item!!.itemId == R.id.record_button)
        {
            //select about, display about activity
            val intent = Intent(this, RecordActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        super.onPause()
        MyApplication.instance.saveRecord()
    }

    fun toastMe( view: View)
    {
        clickCount++
        if( clickCount > 10 )
        {
            val buttonle = findViewById<TextView>(R.id.button)
            buttonle.setText(R.string.button_disable_text)
            buttonle.isEnabled = false
        }
        var cur = getCurrency("HKD","JPY")
    }

    private fun getCurrency( base: String, target: String )
    {
        val url = "http://api.fixer.io/latest?base=$base"
        
        val que = Volley.newRequestQueue(this@MainActivity)
        val req = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener<JSONObject> { response ->
                    applyCurrency(response, target)
                },Response.ErrorListener {
            toast("Something's Wong here...\nlet me check check")
        })
        que.add(req)
    }

    private fun applyCurrency( curObj: JSONObject , target : String)
    {
        var cur = curObj.getJSONObject("rates").getDouble(target)
        val fromEditText = findViewById<EditText>(R.id.from_input)

        if(TextUtils.isEmpty(fromEditText.getText()))
        {
            toast("Please input a invalid number")
        }else {
            val fromInput = fromEditText.getText().toString().toDouble()

            val toEditText = findViewById<EditText>(R.id.to_input)
            toEditText.setText((fromInput * cur).toString())
            addRecord(fromInput,fromInput * cur)
        }
    }

    private fun addRecord( from: Double, to: Double)
    {
        MyApplication.instance.records.add(Record(from.toFloat(),to.toFloat(), Date()))
    }
}
