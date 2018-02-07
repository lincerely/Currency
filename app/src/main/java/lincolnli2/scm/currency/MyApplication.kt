package lincolnli2.scm.currency

import android.app.Application
import io.paperdb.Paper

/**
 * Created by lincolnli2 on 2/7/2018.
 */
class MyApplication : Application(){

    lateinit var records : ArrayList<Record>

    companion object {
        lateinit var instance:MyApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Paper.init(this.baseContext)
        loadFromDatabase()
    }

    private fun loadFromDatabase ()
    {
        // load from database
        records = Paper.book().read<ArrayList<Record>>("records", ArrayList<Record>())
    }

    public fun saveRecord()
    {
        Paper.book().write("records", records);
    }
}