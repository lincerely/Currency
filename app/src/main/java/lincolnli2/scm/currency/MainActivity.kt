package lincolnli2.scm.currency

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.jetbrains.anko.toast
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    var clickCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toastMe( view: View)
    {
        //val myToast = Toast.makeText(this, "Hello Toast!", Toast.LENGTH_SHORT)
        //myToast.show()
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
        val fromEditText = findViewById<EditText>(R.id.to_input)
        val fromInput = fromEditText.getText().toString().toDouble()

        val toEditText = findViewById<EditText>(R.id.from_input)
        toEditText.setText((fromInput*cur).toString())
    }
}
