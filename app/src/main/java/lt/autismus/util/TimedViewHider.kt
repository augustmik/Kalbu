package lt.autismus.util

import android.os.CountDownTimer
import android.view.View
import lt.autismus.CANCEL_TIMER
import lt.autismus.TIMER_TICK

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