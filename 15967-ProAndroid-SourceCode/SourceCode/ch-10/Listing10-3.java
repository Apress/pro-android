android.view.SurfaceHolder holder = surfaceView.getHolder();

// mEgl points to an EGL context interface EGL10
mEglSurface = mEgl.eglCreateWindowSurface(mEglDisplay,
                                 mEglConfig, holder, null);
                                 
mEgl.eglMakeCurrent(mEglDisplay, mEglSurface, mEglSurface,
                  mEglContext);
GL gl = mEgl.getGL();


