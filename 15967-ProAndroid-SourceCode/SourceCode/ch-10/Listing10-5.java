//Destroy surface
mEgl.eglMakeCurrent(mEglDisplay, EGL10.EGL_NO_SURFACE,
                  EGL10.EGL_NO_SURFACE,
                  EGL10.EGL_NO_CONTEXT);
mEgl.eglDestroySurface(mEglDisplay, mEglSurface);

//Destroy context
mEgl.eglDestroyContext(mEglDisplay, mEglContext);

//Disassociate display
mEgl.eglTerminate(mEglDisplay);


