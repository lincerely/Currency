package lincolnli2.scm.currency

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val input = intent.getStringExtra(MainActivity.INPUT_KEY)

        val magicText = findViewById<TextView>(R.id.magicText)
        magicText.text = getString(R.string.magic,input,android.os.Build.DEVICE)
    }
}
