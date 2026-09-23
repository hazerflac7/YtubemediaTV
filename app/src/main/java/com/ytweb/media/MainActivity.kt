package com.ytweb.media

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import org.mozilla.geckoview.GeckoRuntime
import org.mozilla.geckoview.GeckoSession
import org.mozilla.geckoview.GeckoView

class MainActivity : AppCompatActivity() {

    private lateinit var view: GeckoView
    private lateinit var session: GeckoSession
    private lateinit var runtime: GeckoRuntime

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        view = GeckoView(this)
        view.isFocusable = true
        view.isFocusableInTouchMode = true
        setContentView(view)

        runtime = GeckoRuntime.create(this)

        runtime.webExtensionController
            .ensureBuiltIn(
                "resource://android/assets/remote/",
                "remote@ytubemediatv"
            )
            .accept(
                {
                    startYouTube()
                },
                {
                    it?.printStackTrace()
                    startYouTube()
                }
            )
    }

    private fun startYouTube() {
        session = GeckoSession()
        session.open(runtime)
        view.setSession(session)
        view.requestFocus()
        session.loadUri("https://www.youtube.com/tv")
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            when (event.keyCode) {
                KeyEvent.KEYCODE_DPAD_UP,
                KeyEvent.KEYCODE_DPAD_DOWN,
                KeyEvent.KEYCODE_DPAD_LEFT,
                KeyEvent.KEYCODE_DPAD_RIGHT,
                KeyEvent.KEYCODE_DPAD_CENTER,
                KeyEvent.KEYCODE_ENTER,
                KeyEvent.KEYCODE_MEDIA_PLAY,
                KeyEvent.KEYCODE_MEDIA_PAUSE,
                KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE -> {
                    view.requestFocus()
                }
            }
        }

        return super.dispatchKeyEvent(event)
    }
}
