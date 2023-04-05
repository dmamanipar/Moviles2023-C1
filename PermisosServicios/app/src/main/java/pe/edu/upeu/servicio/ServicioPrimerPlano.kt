package pe.edu.upeu.servicio

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.Toast
import android.graphics.Color;


class ServicioPrimerPlano: Service() {

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
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

}