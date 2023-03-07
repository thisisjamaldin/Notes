package com.joma.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class NoteAdapter(val iOnItem: IOnItem): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private var list: MutableList<NoteModel> = ArrayList()

    fun setList(list: MutableList<NoteModel>){
        this.list = list
        notifyDataSetChanged()
    }

    fun getItem(pos:Int): NoteModel{
        return list[pos]
    }

    fun remove(pos: Int){
        list.removeAt(pos)
        notifyItemRemoved(pos)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var imageView = itemView.findViewById<ImageView>(R.id.item_image)
        var textView = itemView.findViewById<TextView>(R.id.item_title)
        var delete = itemView.findViewById<TextView>(R.id.item_delete)
        var edit = itemView.findViewById<TextView>(R.id.item_edit)
        var share = itemView.findViewById<TextView>(R.id.item_share)

        fun bind(pos: Int) {
            itemView.context.getString(R.string.delete)
            textView.text = list[pos].title
            Glide.with(itemView)
                .load(list[pos].imageUri)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
                .into(imageView)
            delete.setOnClickListener {
                iOnItem.delete(pos)
            }
            share.setOnClickListener {
                iOnItem.share(pos)
            }
            edit.setOnClickListener {
                iOnItem.edit(pos)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    interface IOnItem{
        fun delete(pos: Int)
        fun edit(pos: Int)
        fun share(pos: Int)
    }
}