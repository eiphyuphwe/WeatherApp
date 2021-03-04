package com.example.nested_recycler_view


import android.annotation.SuppressLint
import android.graphics.drawable.VectorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aster.app.weather.R
import com.aster.app.weather.data.model.ListItem
import com.aster.app.weather.utils.Convertion
import com.aster.app.weather.utils.DataConverter
import kotlinx.android.synthetic.main.item_view_weather_time_snapshots.view.*


class HomeAdapter() : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()
    var mainWeatherForecastData: Map<String,List<ListItem>> = HashMap()

            fun updateData(mainWeatherForecastList:Map<String,List<ListItem>> )
            {
                this.mainWeatherForecastData = mainWeatherForecastList
            }
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view_weather_time_snapshots, parent, false))
    }

    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tag = position
       // holder.itemView.setOnClickListener(clickListener)
        //holder.tvAnimalType?.text =
        var key =  mainWeatherForecastData.keys.toTypedArray()[position]
        var values = mainWeatherForecastData.get(key)
        holder.itemView?.labelHumidity.text = "Humidity"
        holder.itemView?.labelWeather.text = "Weather"
        holder.itemView?.labelWind.text = "Wind"
        holder.itemView?.valueHumidity?.text = values?.get(0)?.main?.humidity.toString() + "%"
        holder.itemView?.valueWeather?.text = values?.get(0)?.weatherList?.get(0)?.description
        holder.itemView?.textViewMinTemp?.text =
            values?.get(0)?.main?.tempMin?.let { Convertion.convertKelvinTocelsius(it)?.toString()+"°" }
        holder.itemView?.textViewMaxTemp?.text =
            values?.get(0)?.main?.tempMax?.let { Convertion.convertKelvinTocelsius(it)?.toString() +"°"}

        var iconPath = values?.get(0)?.weatherList?.get(0)?.icon
        //var icon =  hcontext.resources.getDrawable(imageid, null)
        val newPath = iconPath?.replace(iconPath, "a$iconPath")
        val imageid = holder.itemView.context.resources.getIdentifier(newPath + "_svg", "drawable", holder.itemView.context.packageName)
        val imageDrawable = holder.itemView.context.resources.getDrawable(imageid, holder.itemView.context.theme) as VectorDrawable
        holder.itemView.imageViewForecastIcon.visibility=View.VISIBLE
        holder.itemView.imageViewForecastIcon.setImageDrawable(imageDrawable)

        val childLayoutManager = LinearLayoutManager(holder.itemView.recyclerViewHoursOfDay.context, LinearLayout.HORIZONTAL, false)

        holder.itemView.recyclerViewHoursOfDay.apply {
            layoutManager = childLayoutManager
            adapter = values?.let { SubAdapter(it) }
            setRecycledViewPool(viewPool)
        }
    }

    override fun getItemCount(): Int {
        return mainWeatherForecastData.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
       // val tvAnimalType = view.tvMain
    }
}

