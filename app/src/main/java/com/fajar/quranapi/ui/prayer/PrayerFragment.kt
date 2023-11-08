package com.fajar.quranapi.ui.prayer

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.fajar.quranapi.R
import com.fajar.quranapi.core.data.remote.network.ApiService
import com.fajar.quranapi.core.data.remote.network.RetrofitClient
import com.fajar.quranapi.databinding.FragmentPrayerBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class PrayerFragment: Fragment(R.layout.fragment_prayer) {

    private var _binding: FragmentPrayerBinding? = null
    private val binding get() = _binding!!
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private val permissionId = 2
    private var comingSoon = ""

    private val format = SimpleDateFormat("HH:mm", Locale.US)
    private val dateFormat = "dd-MM-yyyy HH:mm:ss"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPrayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDailySchedule()
        getCurrentLocation()
        getLocation(requireContext())

    }

    @SuppressLint("SetTextI18n")
    private fun getDailySchedule() {
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiService::class.java)
        lifecycleScope.launch {
            try {
                val date = getCurrentDateTime()
                val currentTime = date.toString("HH:mm")
                val currentDateTime = date.toString("dd-MM-yyy HH:mm:ss")
                var endTime = ""

                val c = Calendar.getInstance()
                c.time = dateFormatter(dateFormat).parse(currentDateTime) ?: date
                c.add(Calendar.DATE, 1)
                val dayTomorrow = c.time.toString("dd")
                val monthTomorrow = c.time.toString("MM")

                binding.tvTanggal.text = date.toString("EEEE, dd MMMM yyyy")

                val response = apiInterface.getScheduleByDay(
                    "1219",
                    date.toString("yyyy"),
                    date.toString("MM"),
                    date.toString("dd")
                )
                if (response.isSuccessful()) {
                    response.body()?.data?.jadwal?.apply {
                        binding.tvJamImsak.text = imsak
                        binding.tvJamShubuh.text = subuh
                        binding.tvJamDzuhur.text = dzuhur
                        binding.tvJamAshar.text = ashar
                        binding.tvJamMaghrib.text = maghrib
                        binding.tvJamIsya.text = isya

                        val current = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            LocalTime.of(
                                currentTime.substring(0, 2).toInt(),
                                currentTime.substring(3, 5).toInt()
                            )
                        } else {
                            TODO("VERSION.SDK_INT < O")
                        }

                        val timeImsak = LocalTime.of(
                            imsak.substring(0, 2).toInt(),
                            imsak.substring(3, 5).toInt()
                        )
                        val timeSubuh = LocalTime.of(
                            subuh.substring(0, 2).toInt(),
                            subuh.substring(3, 5).toInt()
                        )
                        val timeDzuhur = LocalTime.of(
                            dzuhur.substring(0, 2).toInt(),
                            dzuhur.substring(3, 5).toInt()
                        )
                        val timeAshar = LocalTime.of(
                            ashar.substring(0, 2).toInt(),
                            ashar.substring(3, 5).toInt()
                        )
                        val timeMaghrib = LocalTime.of(
                            maghrib.substring(0, 2).toInt(),
                            maghrib.substring(3, 5).toInt()
                        )
                        val timeIsya =
                            LocalTime.of(isya.substring(0, 2).toInt(), isya.substring(3, 5).toInt())

                        binding.tvLocalTime.text = currentTime
                        binding.tvJam.text = when {
                            current < timeImsak || current >= timeIsya -> {
                                val endDate = date.toString("dd-MM-yyyy")
                                val endDateSub = "$endDate $imsak:00"

                                val imsakDate = dateFormatter(dateFormat).parse(endDateSub) ?: date

                                if(c.time.date == imsakDate.date) {
                                    val cImsak = Calendar.getInstance()
                                    cImsak.time = dateFormatter(dateFormat).parse(endDateSub) ?: date
                                    cImsak.add(Calendar.DATE, 1)
                                    endTime = cImsak.time.toString("dd-MM-yyyy HH:mm:ss")

                                    getTomorrowSchedule(dayTomorrow, monthTomorrow)
                                    val day = c.time.toString("EEEE")
                                    val dayDate = c.time.toString("dd MMMM yyyy")
                                    binding.tvTanggal.text = "$day (Besok), $dayDate"
                                } else {
                                    endTime = endDateSub
                                }

                                comingSoon = "Imsak"
                                "Imsak - $imsak"
                            }
                            current < timeSubuh -> {
                                val endDate = date.toString("dd-MM-yyyy")
                                val endDateSub = "$endDate $subuh:00"

                                endTime = endDateSub

                                comingSoon = "Subuh"
                                "Subuh - $subuh"
                            }
                            current < timeDzuhur -> {
                                val endDate = date.toString("dd-MM-yyyy")
                                val endDateSub = "$endDate $dzuhur:00"

                                endTime = endDateSub

                                comingSoon = "Dzuhur"
                                "Dzuhur - $dzuhur"
                            }
                            current < timeAshar -> {
                                val endDate = date.toString("dd-MM-yyyy")
                                val endDateSub = "$endDate $ashar:00"

                                endTime = endDateSub

                                comingSoon = "Ashar"
                                "Ashar - $ashar"
                            }
                            current < timeMaghrib -> {
                                val endDate = date.toString("dd-MM-yyyy")
                                val endDateSub = "$endDate $maghrib:00"

                                endTime = endDateSub

                                comingSoon = "Maghrib"
                                "Maghrib - $maghrib"
                            }
                            current < timeIsya -> {
                                val endDate = date.toString("dd-MM-yyyy")
                                val endDateSub = "$endDate $isya:00"

                                endTime = endDateSub

                                comingSoon = "Isya"
                                "Isya - $isya"
                            }
                            else -> {
                                comingSoon = "null"
                                "null"
                            }
                        }
                    }

                    initShalatCountDown(endTime)

                } else {
                 //   Toast.makeText(
                 //       ContentProviderCompat.requireContext(),
                //        response.errorBody().toString(),
                //        Toast.LENGTH_LONG
              //      ).show()
                }
            } catch (Ex: Exception) {
                Log.e("Error", Ex.localizedMessage)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getTomorrowSchedule(day: String, month: String) {
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiService::class.java)
        lifecycleScope.launch {
            try {
                val response = apiInterface.getScheduleByDay(
                    "1219",
                    "2023",
                    day,
                    month
                )
                if (response.isSuccessful()) {
                    response.body()?.data?.jadwal?.apply {
                        binding.tvJamImsak.text = imsak
                        binding.tvJamShubuh.text = subuh
                        binding.tvJamDzuhur.text = dzuhur
                        binding.tvJamAshar.text = ashar
                        binding.tvJamMaghrib.text = maghrib
                        binding.tvJamIsya.text = isya

                        binding.tvJam.text = "Imsak - $imsak"
                    }

                } else {
                 //   Toast.makeText(
                 //       ContentProviderCompat.requireContext(),
                 //       response.errorBody().toString(),
                 //       Toast.LENGTH_LONG
                //    ).show()
                }
            } catch (Ex: Exception) {
                Log.e("Error", Ex.localizedMessage)
            }
        }
    }

    private fun getCurrentLocation() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions(context: Context): Boolean {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            permissionId
        )
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == permissionId) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLocation(requireContext())
            }
        }
    }

    private fun initShalatCountDown(endDate: String) {
        val prefixFormat = "%02d"
        val date = Date()

        val eventDate = dateFormatter(dateFormat)
            .parse(endDate) ?: date

        val currentDateTime = getCurrentDateTime()
        val currentDateText = currentDateTime.toString("dd-MM-yyyy HH:mm:ss")

        val eventCurrentDate = dateFormatter(dateFormat).parse(currentDateText) ?: date

        viewLifecycleOwner.coroutinesCountDown(repeatMillis = 1000L) {
            val currentDate = Date()
            val eventDateInMillis = eventDate.time - currentDate.time
            val eventCurrentInMillis = eventCurrentDate.time

            val hours = String.format(
                prefixFormat,
                (eventDateInMillis.div(60 * 60 * 1000)) % 24
            )

            val currentHours = String.format(
                prefixFormat,
                (eventCurrentInMillis.div(60 * 60 * 1000)) % 24
            )

            val minutes = String.format(
                prefixFormat,
                (eventDateInMillis.div(60 * 1000) % 60)
            )

            val currentMinutes = String.format(
                prefixFormat,
                (eventCurrentInMillis.div(60 * 1000) % 60)
            )

            val seconds =
                String.format(prefixFormat, (eventDateInMillis.div(1000)) % 60)

            binding.apply {
                val data = "$hours Jam $minutes Menit $seconds Detik menuju"
            //    binding.tvHitung.text = data
            //   binding.tvSholat.text = comingSoon

//                binding.tvLtime.text = "$currentHours:$currentMinutes"
            }
        }
    }

    @SuppressLint("MissingPermission", "SetTextI18n")
    private fun getLocation(context: Context) {
        if (checkPermissions(context)) {
            if (isLocationEnabled()) {
                mFusedLocationClient.lastLocation.addOnCompleteListener(requireActivity()) { task ->
                    val location: Location? = task.result
                    if (location != null) {
                        val geocoder = Geocoder(context, Locale.getDefault())
                        val list: List<Address> =
                            geocoder.getFromLocation(location.latitude, location.longitude, 1)
                                .orEmpty()
                        binding.apply {
                            val city = "${list[0].locality.substring(10)}, ${list[0].subAdminArea}"
                            tvPin.text = city
                        }
                    }
                }
            } else {
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    private fun dateFormatter(format: String = "yyyy-MM-dd"): SimpleDateFormat {
        val locale = Locale("id", "ID")

        return SimpleDateFormat(format, locale).apply {
            timeZone = TimeZone.getTimeZone("GMT+07:00")
        }
    }

    private fun LifecycleOwner.coroutinesCountDown(
        delayMillis: Long = 0,
        repeatMillis: Long = 0,
        action: suspend () -> Unit
    ) = lifecycleScope.launch {
        delay(delayMillis)
        if (repeatMillis > 0) {
            while (true) {
                action.invoke()
                delay(repeatMillis)
            }
        } else action()
    }




}