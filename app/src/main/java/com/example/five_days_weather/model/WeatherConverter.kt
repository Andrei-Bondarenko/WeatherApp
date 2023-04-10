package com.example.five_days_weather.model

import com.example.five_days_weather.api.model.*


object WeatherConverter {
    fun fromNetwork(response: WeatherDataResponse): WeatherData {
        return WeatherData(
            cod = response.cod,
            message = response.message,
            listWeather = fromNetwork(response.listWeather),
            cnt = response.cnt,
            city = fromNetwork(response.city)
        )
    }


    private fun fromNetwork(response: List<ListWeatherResponse>): List<ListWeather> {
        return response.map { data ->
            ListWeather(
                main = fromNetwork(data.main),
                weather = fromNetwork(data.weather),
                clouds = fromNetwork(data.clouds),
                wind = fromNetwork(data.wind),
                visibility = data.visibility,
                pop = data.pop,
                sys = fromNetwork(data.sys),
                dt_txt = data.dt_txt
            )
        }
    }


    private fun fromNetwork(response: SysResponse): Sys {
        return Sys(
            pod = response.pod
        )
    }


    private fun fromNetwork(response: WindResponse): Wind {
        return Wind(
            speed = response.speed,
            deg = response.deg,
            gust = response.gust
        )
    }


    private fun fromNetwork(response: CloudsResponse): Clouds {
        return Clouds(
            all = response.all
        )
    }


    private fun fromNetwork(response: MainResponse): Main {
        return Main(
            temp = response.temp,
            feels_like = response.feels_like,
            temp_min = response.temp_min,
            temp_max = response.temp_max,
            pressure = response.pressure,
            humidity = response.humidity,
            sea_level = response.sea_level,
            grnd_level = response.grnd_level
        )
    }

    @JvmName("fromNetwork1")
    private fun fromNetwork(response: List<WeatherResponse>): List<Weather> {
        return response.map { data ->
            Weather(
                id = data.id,
                main = data.main,
                description = data.description,
                icon = data.icon
            )
        }
    }


    private fun fromNetwork(response: CityResponse): City {
        return City(
            id = response.id,
            name = response.name,
            coord = fromNetwork(response.coord),
            country = response.country,
            population = response.population,
            timezone = response.timezone,
            sunrise = response.sunrise,
            sunset = response.sunset
        )
    }

    private fun fromNetwork(response: CoordResponse): Coord {
        return Coord (
            lat = response.lat,
            lon = response.lon
                )
    }
}