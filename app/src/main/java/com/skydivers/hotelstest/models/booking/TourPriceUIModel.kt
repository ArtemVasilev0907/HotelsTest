package com.skydivers.hotelstest.models.booking

import com.skydivers.hotelstest.models.BaseUIModel

data class TourPriceUIModel(
    var tour:Float = 0f,
    var oil:Float = 0f,
    var service:Float = 0f,
    var summary:Float = 0f
):BaseUIModel{
    fun calculate(tourData:List<BaseUIModel>):TourPriceUIModel{

       var tourists = 1

       for( i in tourData.indices){
           if (tourData[i] is TouristUIModel)
               tourists++
       }
        tour*=tourists
        oil*=tourists
        service*=tourists
        summary = tour+oil+service
       return TourPriceUIModel(tour, oil, service, summary)
    }

    override fun isFilled(): Boolean = true
}
