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
        setContentView(view)

        runtime = GeckoRuntime.create(this)
        session = GeckoSession()
        session.open(runtime)
        view.setSession(session)
        session.loadUri("https://www.youtube.com/tv")
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        // Let Gecko/YouTube receive normal DPAD, enter and media events.
        // Custom 4-second seeking will be added through the WebExtension bridge.
        return super.dispatchKeyEvent(event)
    }
}
