//filename: EglHelper.java
public class EglHelper
{
   EGL10 mEgl; EGLDisplay mEglDisplay; EGLSurface mEglSurface;
   EGLConfig mEglConfig; EGLContext mEglContext;

   public EglHelper(){}

   public void start(int[] configSpec)
   {
      mEgl = (EGL10) EGLContext.getEGL();
      mEglDisplay = mEgl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
      int[] version = new int[2];

      mEgl.eglInitialize(mEglDisplay, version);
      EGLConfig[] configs = new EGLConfig[1];
      int[] num_config = new int[1];

      mEgl.eglChooseConfig(mEglDisplay, configSpec, configs, 1,
      num_config);
      mEglConfig = configs[0];

      mEglContext = mEgl.eglCreateContext(mEglDisplay, mEglConfig,
                                 EGL10.EGL_NO_CONTEXT, null);
      mEglSurface = null;
   }

   public GL createSurface(SurfaceHolder holder) {
      if (mEglSurface != null) {
         mEgl.eglMakeCurrent(mEglDisplay, EGL10.EGL_NO_SURFACE,
                        EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
         mEgl.eglDestroySurface(mEglDisplay, mEglSurface);
      }
      mEglSurface = mEgl.eglCreateWindowSurface(mEglDisplay,
                                    mEglConfig, holder, null);
      mEgl.eglMakeCurrent(mEglDisplay, mEglSurface, mEglSurface,
                                    mEglContext);
      GL gl = mEglContext.getGL();
      return gl;
   }
   
   public boolean swap() {
      mEgl.eglSwapBuffers(mEglDisplay, mEglSurface);
      return mEgl.eglGetError() != EGL11.EGL_CONTEXT_LOST;
   }

   public void finish() {
      if (mEglSurface != null) {
         mEgl.eglMakeCurrent(mEglDisplay, EGL10.EGL_NO_SURFACE,
                                 EGL10.EGL_NO_SURFACE,
                                 EGL10.EGL_NO_CONTEXT);
         mEgl.eglDestroySurface(mEglDisplay, mEglSurface);
         mEglSurface = null;
      }
      if (mEglContext != null) {
         mEgl.eglDestroyContext(mEglDisplay, mEglContext);
         mEglContext = null;
      }
      if (mEglDisplay != null) {
         mEgl.eglTerminate(mEglDisplay);
         mEglDisplay = null;
      }
   }
}


