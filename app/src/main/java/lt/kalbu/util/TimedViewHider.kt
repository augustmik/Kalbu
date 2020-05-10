package lt.kalbu.util

import android.os.CountDownTimer
import android.view.View
import lt.kalbu.CANCEL_TIMER
import lt.kalbu.TIMER_TICK

class TimedViewHider {

    fun hideViewTimer(view: View){
        object : CountDownTimer(CANCEL_TIMER,TIMER_TICK){
            override fun onTick(millisToEnd: Long) {}
            override fun onFinish() {
                view.visibility = View.GONE
            }
        }.start()
    }
}