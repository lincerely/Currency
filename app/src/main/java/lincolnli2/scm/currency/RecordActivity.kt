package lincolnli2.scm.currency

import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import io.paperdb.Paper

class RecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        val rView = findViewById<RecyclerView>(R.id.rView)

        val adapter = RecordAdapter(this,  MyApplication.instance.records)
        rView.adapter = adapter
        rView.layoutManager = LinearLayoutManager(this)
        rView.hasFixedSize()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.record_menu, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == R.id.record_menu_clear)
        {
            MyApplication.instance.records.clear()
            MyApplication.instance.saveRecord()
        }
        return super.onOptionsItemSelected(item)
    }
}
