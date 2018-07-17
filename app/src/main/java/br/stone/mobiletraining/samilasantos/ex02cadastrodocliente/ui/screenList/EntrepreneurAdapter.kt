package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.screenList

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.R
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.uc.GetCompanyInitials
import kotlinx.android.synthetic.main.item_recycler_entrepreneurs.view.*

class EntrepreneurAdapter(private val entrepreneurs: List<Entrepreneur>, private val listener : OnItemClickListener) : RecyclerView.Adapter<EntrepreneurAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder = ItemViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_entrepreneurs, parent, false))

    override fun getItemCount(): Int = entrepreneurs.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = holder.bind(entrepreneurs[position], listener)

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(entrepreneur: Entrepreneur, listener : OnItemClickListener) {
            itemView.text_company_initials.text = GetCompanyInitials.process(entrepreneur.tradeName)
            itemView.text_company_name.text = entrepreneur.tradeName
            itemView.text_owner_name.text = entrepreneur.fullName

            itemView.constraint_item.setOnClickListener { listener.onClick(entrepreneur) }
        }
    }

    interface OnItemClickListener  {
        fun onClick (entrepreneur: Entrepreneur)
    }
}