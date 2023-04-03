package com.example.weather.model

import com.example.weather.api.model.*

object WeatherConverter {
    fun fromNetwork(response: WeatherDataResponse): WeatherData {
        return WeatherData(
            clouds = fromNetwork(response.clouds),
            coord = fromNetwork(response.coord),
            main = fromNetwork(response.main),
            sys = fromNetwork(response.sys),
            weather = fromNetwork(response.weather),
            wind = fromNetwork(response.wind),
            base =  response.base,
            visibility = response.visibility,
            dt = response.dt,
            timezone = response.timezone,
            id = response.id,
            name = response.name,
            cod = response.cod
        )
    }


    private fun fromNetwork(response: CoordResponse): Coord {
        return Coord(
            lon = response.lon,
            lat = response.lat
        )
    }

    private fun fromNetwork(response: CloudsResponse): Clouds {
        return Clouds(
            all = response.all
        )
    }

    private fun fromNetwork(response: MainDataResponse): MainData {
        return MainData(
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

    private fun fromNetwork(response: SysResponse): Sys {
        return Sys(
            type = response.type,
            id = response.id,
            country = response.country,
            sunrise = response.sunrise,
            sunset = response.sunset
        )
    }

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

    private fun fromNetwork(response: WindResponse): Wind {
        return Wind(
            speed = response.speed,
            deg = response.deg,
            gust = response.gust
        )
    }
}