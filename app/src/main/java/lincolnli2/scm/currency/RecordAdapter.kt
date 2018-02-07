package lincolnli2.scm.currency

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.w3c.dom.Text
import java.security.AccessControlContext

/**
 * Created by lincolnli2 on 2/7/2018.
 */
class RecordAdapter( private val context: Context, private val list: List<Record>) :
        RecyclerView.Adapter<RecordAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var fromTextView: TextView
        var toTextView: TextView
        var timeTextView: TextView

        init{
            fromTextView = itemView.findViewById<TextView>(R.id.from_card_text)
            toTextView = itemView.findViewById<TextView>(R.id.to_card_text)
            timeTextView = itemView.findViewById<TextView>(R.id.time_card_text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecordAdapter.ViewHolder {
        val view : View = LayoutInflater.from(parent?.context).inflate(R.layout.item_record, parent, false)
        val card = view.findViewById<CardView>(R.id.card_view)
        card.maxCardElevation = 2.0f
        card.radius = 5f
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecordAdapter.ViewHolder, position: Int) {
        val record : Record = list.get(position)
        holder.toTextView.text = record.to.toString()
        holder.fromTextView.text = record.from.toString()
        holder.timeTextView.text = record.time.toString()
    }

    private fun showPopupMeun(view:View)
    {

    }

    override fun getItemCount(): Int {
        return list.size
    }
}