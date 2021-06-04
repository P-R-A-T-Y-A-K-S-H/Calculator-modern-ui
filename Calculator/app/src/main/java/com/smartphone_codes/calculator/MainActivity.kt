package com.smartphone_codes.calculator

import android.app.Activity
import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import java.text.DecimalFormat
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Calculator)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT in 19..20) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        //make fully Android Transparent Status bar
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }

        val maindisp = findViewById<TextView>(R.id.display)
        val predisp = findViewById<TextView>(R.id.predisp)
        maindisp.text = ""
        predisp.text = ""


    }

    private val hist = mutableListOf<String>()
    private var histIndeX: Int = 0
    private val lst = mutableListOf<Char>('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
    private val lst1 = mutableListOf<Char>('÷', '+', '-', 'x')

    private fun processor() {
        val maindisp: TextView = findViewById(R.id.display)
        val txt: String = maindisp.text.toString()
        val predisp: TextView = findViewById(R.id.predisp)
        Log.i("MainActivity", "called processor")
        var i: Int = 0
        var numans: Double = 0.0
        var temp: String = ""
        var mmtx: String = ""
        Log.i("MainActivity", "for loop start")
        while (i < txt.length) {
            Log.i("MainActivity", "in loop ${txt[i]}:[$i]")

            if (lst.contains(txt[i]) || txt[i] == '.') {
                Log.i("MainActivity", "NO opr append temp:${temp}")
                temp += txt[i]
                i++
            } else if (txt[i] == '+' && !lst1.contains(txt.last()) && txt[i] != '√' && txt[i] != '²') {
                Log.i("MainActivity", "opr + found")
                i++
                if (txt[i] == '√') {
                    i++
                    Log.e("MainActivity", "Root b/w Add found")
                    while (i < txt.length) {
                        if (i < txt.length && lst.contains(txt[i]) || txt[i] == '.') {
                            Log.e("MainActivity", "Root b/w Add found")
                            if (i == txt.length - 1) {
                                Log.e("MainActivity", "root last char going to increase mmtx:${txt[i]}")
                                mmtx += txt[i]
                                var mmtxtemp: Double = sqrt(mmtx.toDouble())
                                mmtx = mmtxtemp.toString()
                                Log.e("MainActivity", "Applied  root to mmtx:$mmtx within mmtxtemp:$mmtxtemp")
                                break
                            } else {
                                mmtx += txt[i]
                                Log.i("MainActivity", "sqrt + increasing mmtx:${mmtx}")
                                i++
                            }
                        } else {
                            var mmtxtemp: Double = sqrt(mmtx.toDouble())
                            mmtx = mmtxtemp.toString()
                            Log.e("MainActivity", "Applied  root to mmtx:$mmtx within mmtxtemp:$mmtxtemp")
                            break
                        }
                    }
                } else {
                    while (i < txt.length) {
                        Log.e("MainActivity", "+ b/w Add found i:$i txt:${txt[i]}")
                        if (lst.contains(txt[i]) || txt[i] == '.') {
                            mmtx += txt[i]
                            Log.i("MainActivity", "+ increasing mmtx:${mmtx}")
                            i++
                        } else {
                            break
                        }
                    }
                }
                if (temp.isNotEmpty()) {
                    Log.i("MainActivity", " + temp:${temp} not empty ")
                    numans += mmtx.toDouble() + temp.toDouble()
                    Log.i("MainActivity", "added mmtx:${mmtx} to temp:${temp} = numans:$numans")
                    temp = ""
                    mmtx = ""
                } else {
                    Log.i("MainActivity", "temp empty numans:$numans")
                    numans += mmtx.toDouble()
                    Log.i("MainActivity", "added numans:$numans to mmtx:$mmtx")
                    mmtx = ""

                }
            } else if (txt[i] == '-' && !lst1.contains(txt.last())) {
                Log.i("MainActivity", "opr - found")
                i++
                if (txt[i] == '√') {
                    i++
                    Log.e("MainActivity", "Root b/w Add found")
                    while (i < txt.length) {
                        if (i < txt.length && lst.contains(txt[i]) || txt[i] == '.') {
                            Log.e("MainActivity", "Root b/w Add found")
                            if (i == txt.length - 1) {
                                Log.e("MainActivity", "root last char going to increase mmtx:${txt[i]}")
                                mmtx += txt[i]
                                var mmtxtemp: Double = sqrt(mmtx.toDouble())
                                mmtx = mmtxtemp.toString()
                                Log.e("MainActivity", "Applied  root to mmtx:$mmtx within mmtxtemp:$mmtxtemp")
                                break
                            } else {
                                mmtx += txt[i]
                                Log.i("MainActivity", "sqrt + increasing mmtx:${mmtx}")
                                i++
                            }
                        } else {
                            var mmtxtemp: Double = sqrt(mmtx.toDouble())
                            mmtx = mmtxtemp.toString()
                            Log.e("MainActivity", "Applied  root to mmtx:$mmtx within mmtxtemp:$mmtxtemp")
                            break
                        }
                    }
                } else {
                    while (i < txt.length) {
                        if (lst.contains(txt[i]) || txt[i] == '.') {
                            mmtx += txt[i]
                            Log.i("MainActivity", " - increasing temp :$temp")
                            i++
                        } else {
                            break
                        }
                    }
                }
                if (temp.isNotEmpty()) {
                    numans += temp.toDouble() - mmtx.toDouble()
                    Log.i("MainActivity", "temp! subtracting mmtx:$mmtx from temp:$temp")
                    temp = ""
                    mmtx = ""
                } else {
                    numans -= mmtx.toDouble()
                    Log.i("MainActivity", "temp subtracting numans:$numans from mmtx:$mmtx")
                    mmtx = ""
                }
            } else if (txt[i] == 'x' && !lst1.contains(txt.last())) {
                Log.i("MainActivity", "opr x found")
                i++
                if (txt[i] == '√') {
                    i++
                    Log.e("MainActivity", "Root b/w Add found")
                    while (i < txt.length) {
                        if (i < txt.length && lst.contains(txt[i]) || txt[i] == '.') {
                            Log.e("MainActivity", "Root b/w Add found")
                            if (i == txt.length - 1) {
                                Log.e("MainActivity", "root last char going to increase mmtx:${txt[i]}")
                                mmtx += txt[i]
                                var mmtxtemp: Double
                                mmtxtemp = sqrt(mmtx.toDouble())
                                mmtx = mmtxtemp.toString()
                                Log.e("MainActivity", "Applied  root to mmtx:$mmtx within mmtxtemp:$mmtxtemp")
                                break
                            } else {
                                mmtx += txt[i]
                                Log.i("MainActivity", "sqrt + increasing mmtx:${mmtx}")
                                i++
                            }
                        } else {
                            var mmtxtemp: Double = sqrt(mmtx.toDouble())
                            mmtx = mmtxtemp.toString()
                            Log.e("MainActivity", "Applied  root to mmtx:$mmtx within mmtxtemp:$mmtxtemp")
                            break
                        }
                    }
                } else {
                    while (i < txt.length) {
                        if (lst.contains(txt[i]) || txt[i] == '.') {
                            mmtx += txt[i]
                            Log.i("MainActivity", " x increasing temp :$temp")
                            i++
                        } else {
                            break
                        }
                    }
                }
                if (temp.isNotEmpty()) {
                    numans += mmtx.toDouble() * temp.toDouble()
                    Log.i("MainActivity", "temp! mul numans:$numans + mmtx:$mmtx x temp:$temp")
                    temp = ""
                    mmtx = ""
                } else {
                    numans *= mmtx.toDouble()
                    Log.i("MainActivity", "temp mul numans:$numans x mmtx:$mmtx ")
                    mmtx = ""
                }
            } else if (txt[i] == '÷' && !lst1.contains(txt.last())) {
                Log.i("MainActivity", "opr / found")
                i++
                if (txt[i] == '√') {
                    i++
                    Log.e("MainActivity", "Root b/w Add found")
                    while (i < txt.length) {
                        if (i < txt.length && lst.contains(txt[i]) || txt[i] == '.') {
                            Log.e("MainActivity", "Root b/w Add found")
                            if (i == txt.length - 1) {
                                Log.e("MainActivity", "root last char going to increase mmtx:${txt[i]}")
                                mmtx += txt[i]
                                var mmtxtemp: Double = sqrt(mmtx.toDouble())
                                mmtx = mmtxtemp.toString()
                                Log.e("MainActivity", "Applied  root to mmtx:$mmtx within mmtxtemp:$mmtxtemp")
                                break
                            } else {
                                mmtx += txt[i]
                                Log.i("MainActivity", "sqrt + increasing mmtx:${mmtx}")
                                i++
                            }
                        } else {
                            var mmtxtemp: Double = sqrt(mmtx.toDouble())
                            mmtx = mmtxtemp.toString()
                            Log.e("MainActivity", "Applied  root to mmtx:$mmtx within mmtxtemp:$mmtxtemp")
                            break
                        }
                    }
                } else {
                    while (i < txt.length) {
                        if (lst.contains(txt[i]) || txt[i] == '.') {
                            mmtx += txt[i]
                            i++
                            Log.i("MainActivity", " x increasing mmtx :$mmtx")
                        } else {
                            break
                        }
                    }
                }
                if (temp.isNotEmpty()) {
                    numans += temp.toDouble() / mmtx.toDouble()
                    Log.i("MainActivity", "temp! mul numans:$numans + temp:$temp / mmtx:$mmtx")
                    temp = ""
                    mmtx = ""
                } else {
                    numans /= mmtx.toDouble()
                    Log.i("MainActivity", "temp mul numans:$numans / mmtx:$mmtx ")
                    mmtx = ""
                }
            } else if (txt[i] == '√' && !lst1.contains(txt.last())) {
                Log.i("MainActivity", "opr ^ found")
                i++
                while (i < txt.length) {
                    if (lst.contains(txt[i]) || txt[i] == '.') {
                        mmtx += txt[i]
                        i++
                    } else {
                        break
                    }
                }
                if (temp.isNotEmpty()) {
                    numans += sqrt(mmtx.toDouble()) * temp.toDouble()
                    Log.e("MainActivity", "temp! : numans:$numans + temp:$temp * sqrtmmtx:$mmtx")
                    temp = ""
                    mmtx = ""
                } else {
                    if (numans != 0.0) {
                        numans *= sqrt(mmtx.toDouble())
                        mmtx = ""
                    } else {
                        numans = sqrt(mmtx.toDouble())
                        Log.e("MainActivity", "temp : numans:$numans = sqrtmmtx:$mmtx ")
                        mmtx = ""
                    }
                }
            }
        }
        predisp.text = preTextHandler(numans)
        Log.e("Manactivity", "Updated the predisp :${predisp.text}")
    }


    fun numbtndisp(view: View) {

        val maindisp: TextView = findViewById(R.id.display)
        dispSizeHandler()
        val btnclicked = findViewById<Button>(view.id)
        if (view.id == R.id.dot && maindisp.text.contains(".")) {
            var i = maindisp.length() - 1
            var cnd: Boolean = false
            while (true) {
                Log.e("MyActivity", "val btn i:$i")
                if (maindisp.text[i] == '.') {
                    cnd = false
                    break
                } else if (lst1.contains(maindisp.text[i])) {
                    cnd = true
                    break
                }
                i--
            }
            if (cnd) {
                maindisp.append(".")
            } else {
                //Deciaml number can have only one dot
            }
        } else {
            maindisp.append(btnclicked.text)
        }
        Log.i("MainActivity", "going to call processor")
        processor()

    }

    fun operatorBtnDisp(view: View) {
        val maindisp: TextView = findViewById(R.id.display)
        val predisp: TextView = findViewById(R.id.predisp)
        dispSizeHandler()
        val btnclicked = findViewById<Button>(view.id)


        if (maindisp.text.isEmpty()) {
            //Operators can't be used before no text instead of root
            when (view.id) {
                R.id.root -> maindisp.append("√")
            }
        } else if (lst.contains(maindisp.text.last()) || btnclicked.id == R.id.equal) {

            when (view.id) {
                R.id.root -> maindisp.append("√")
                R.id.div -> maindisp.append("÷")
                R.id.mul -> maindisp.append("x")
                R.id.sub -> maindisp.append("-")
                R.id.add -> maindisp.append("+")
                R.id.equal -> {
                    hist.add(maindisp.text.toString())
                    histIndeX = hist.size - 1
                    maindisp.text = predisp.text
                    predisp.text = null
                    dispSizeHandler()
                }

            }
        } else if (lst.contains(maindisp.text.last()) || lst1.contains(maindisp.text.last())) {
            when (view.id) {
                R.id.root -> {maindisp.append("√")}
                R.id.div -> {maindisp.text = maindisp.text.substring(0, maindisp.text.length - 1)
                                maindisp.append("÷")}
                R.id.mul -> {maindisp.text = maindisp.text.substring(0, maindisp.text.length - 1)
                    maindisp.append("x")}
                R.id.sub -> {maindisp.text = maindisp.text.substring(0, maindisp.text.length - 1)
                    maindisp.append("-")}
                R.id.add -> {maindisp.text = maindisp.text.substring(0, maindisp.text.length - 1)
                    maindisp.append("+")}
            }
        }
    }

    fun clrdisp(view: View) {

        val maindisp: TextView = findViewById(R.id.display)
        val predisp: TextView = findViewById(R.id.predisp)
        dispSizeHandler()

        if (view.id == R.id.clearbtn) {
            maindisp.text = ""
            predisp.text = ""
        } else if (view.id == R.id.bck && maindisp.text.isNotEmpty()) {
            if (lst1.contains(maindisp.text.last()) || maindisp.text.last() == '.' || maindisp.text.last() == '√' && maindisp.text.isNotEmpty()) {
//                we don't need process if nothin is done i.e. operator in last
            } else {
                processor()
            }
            maindisp.text = maindisp.text.substring(0, maindisp.text.length - 1)

        } else if (maindisp.text.isEmpty()) {
            predisp.text = ""
        }
    }


// Screen Handlers downside

    private fun dispSizeHandler() {

        val maindisp: TextView = findViewById(R.id.display)

        if (maindisp.text.length > 7) {
            maindisp.textSize = 35F
        } else if (maindisp.text.length <= 7) {
            maindisp.textSize = 70F

        }

    }

    private fun preTextHandler(pretxt: Double): String {
        val decimalFormatter = DecimalFormat("#")
        return if (pretxt.toString().last() == '0' && pretxt.toString()[pretxt.toString().length - 2] == '.') {
            decimalFormatter.format(pretxt).toString()
        } else {
            pretxt.toString()
        }
    }

    fun rccHistory(view: View) {
        val maindisp: TextView = findViewById(R.id.display)
        if (hist.isNotEmpty()) {
            maindisp.text = hist[histIndeX]
            hist.removeAt(histIndeX)
            histIndeX--
            dispSizeHandler()
            processor()
        } else {
            //no hist noting to do !
        }
    }

    private fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
        val win: Window = activity.window
        val winParams: WindowManager.LayoutParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun darkModeSwitch(view: View) {
        val handler = Handler()
        val btn: View = findViewById(R.id.anime)
        val switchbtn: ImageView = findViewById(R.id.darkMode)
        val ani: Animation = AnimationUtils.loadAnimation(this, R.anim.circle_explosion)

        val context = baseContext
        when (context?.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_NO -> {
                btn.setBackgroundResource(R.drawable.circleblack)
                btn.isVisible = true
                handler.postDelayed({ delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES }, 400)
                ani.duration = 700
                btn.startAnimation(ani)
                Log.e("MainActivity", "done black mode ")
                btn.isVisible = false
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                btn.setBackgroundResource(R.drawable.circlewhite)
                btn.isVisible = true
                handler.postDelayed({ delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO }, 650)
                ani.duration = 700
                btn.startAnimation(ani)
                Log.e("MainActivity", "done light mode")
                btn.isVisible = false
            }
        }

    }

}