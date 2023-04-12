package pe.edu.upeu.servicio

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.Toast
import android.graphics.Color;
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import pe.edu.upeu.ContextoActUtil


class ServicioPrimerPlano: Service() {

    private var locationCallback: LocationCallback? = null
    var fusedLocationClient: FusedLocationProviderClient? = null

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        locationCallback?.let {
            val locationRequest = LocationRequest.create().apply {
                interval = 10000
                fastestInterval = 5000
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            }
            fusedLocationClient?.requestLocationUpdates(locationRequest, it, Looper.getMainLooper())
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Thread {
            while (true) {
                Log.e("Service", "Service is running...")
                Handler(Looper.getMainLooper()).post{
                    val toast: Toast=Toast.makeText(this, "El servicio se va ejecutando:", Toast.LENGTH_SHORT)
                    toast.view!!.setBackgroundColor(Color.parseColor("#2df6b0"))
                    toast.show()
                }
                try {
                    Thread.sleep(2000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()

        val CHANNELID = "Foreground Service ID"
        val channel = NotificationChannel(
            CHANNELID,
            CHANNELID,
            NotificationManager.IMPORTANCE_LOW
        )
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        val notification: Notification.Builder = Notification.Builder(this, CHANNELID)
            .setContentText("Service is running")
            .setContentTitle("Service enabled")
            //.setSmallIcon(R.drawable.imagenperu)
        startForeground(1001, notification.build())
        //return START_REDELIVER_INTENT
        //return super.onStartCommand(intent, flags, startId)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(
            ContextoActUtil.CONTEXTO_APPX)
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                for (lo in p0.locations) {
                    Log.e("LATLON", "Lat: ${lo.latitude} Lon: ${lo.longitude}")

                }
            }
        }
        startLocationUpdates()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

}