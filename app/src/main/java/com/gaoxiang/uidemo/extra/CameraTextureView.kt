package com.gaoxiang.uidemo.extra

import android.content.Context
import android.graphics.SurfaceTexture
import android.util.AttributeSet
import android.view.TextureView

class CameraTextureView : TextureView, TextureView.SurfaceTextureListener {
    companion object {
        private const val TAG = "CameraTextureView"
    }

    class OpenCameraRunnable : Runnable {
        override fun run() {
        }
    }

    var mSurface:SurfaceTexture? = null
        private set
    private var mWidth:Int = 0
    private var mHeight:Int = 0


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        surfaceTextureListener = this
    }

    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture?, width: Int, height: Int) {
        mSurface = surface
        mWidth = width
        mHeight = height
        Thread(OpenCameraRunnable()).start()
    }

    override fun onSurfaceTextureUpdated(surface: SurfaceTexture?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSurfaceTextureAvailable(surface: SurfaceTexture?, width: Int, height: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}