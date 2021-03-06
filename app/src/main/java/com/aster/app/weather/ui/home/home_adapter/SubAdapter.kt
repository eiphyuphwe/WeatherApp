package com.example.nested_recycler_view


import android.graphics.drawable.VectorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aster.app.weather.R
import com.aster.app.weather.data.model.ListItem
import com.aster.app.weather.utils.Conversion
import kotlinx.android.synthetic.main.item_view_weather_hours_of_day.view.*


class SubAdapter(
    val subItemList: List<ListItem>
    ) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return subItemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.tvSubType?.text = subHeadingData.subData.get(position).subHeading
        holder.tvTemp?.text =
            subItemList?.get(position)?.main?.temp?.let {
                Conversion.convertKelvinTocelsius(it).toString() + "°"
            }
        holder.tvViewHourofDay?.text = Conversion.timeConversion(subItemList?.get(position)?.date)

        //set image
        var iconPath = subItemList?.get(position)?.weatherList.get(0)?.icon
        val newPath = iconPath.replace(iconPath, "a$iconPath")
        val imageid = holder.view.context.resources.getIdentifier(
            newPath + "_svg",
            "drawable",
            holder.view.context.packageName
        )
        val imageDrawable = holder.view.context.resources.getDrawable(
            imageid,
            holder.view.context.theme
        ) as VectorDrawable
        holder.tvImage.visibility = View.VISIBLE
        holder.tvImage.setImageDrawable(imageDrawable)
        // holder.tvImage.setImageResource(R.drawable.a01d_svg)



    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view_weather_hours_of_day, parent, false))
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val view = view
    val tvViewHourofDay = view.textViewHourOfDay
    val tvTemp = view.textViewTemp
    val tvImage = view.imageViewForecastIcon
}
