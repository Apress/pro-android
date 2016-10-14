// filename: OpenGLDrawingThread.java
class OpenGLDrawingThread extends Thread
{
   private boolean mDone, mPaused, mHasFocus;
   private boolean mHasSurface, mContextLost, mSizeChanged;
   private int mWidth,mHeight;

   private Renderer mRenderer;
   private EglHelper mEglHelper;
   private OpenGLTestHarness pSv = null;

   OpenGLDrawingThread(OpenGLTestHarness sv, Renderer renderer) {
      super();
      mDone = false; mWidth = 0; mHeight = 0;
      mRenderer = renderer; mSizeChanged = false;
      setName("GLThread");
      pSv = sv;
   }

   @Override
   public void run() {
      try {
         try {
            OpenGLTestHarness.sEglSemaphore.acquire();
         } catch (InterruptedException e) {
            return;
         }
         guardedRun();
      } catch (InterruptedException e) {
         // fall thru and exit normally
      } finally {
         OpenGLTestHarness.sEglSemaphore.release();
      }
   }

   private void guardedRun() throws InterruptedException {
      mEglHelper = new EglHelper();
      int[] configSpec = mRenderer.getConfigSpec();
      mEglHelper.start(configSpec);
      GL10 gl = null;
      boolean tellRendererSurfaceCreated = true;
      boolean tellRendererSurfaceChanged = true;

      while (!mDone)
      {
         int w, h;
         boolean changed;
         boolean needStart = false;

         synchronized (this) {
            if (mPaused) {
               Log.d("x", "Paused");
               mEglHelper.finish();
               needStart = true;
            }
            if(needToWait()) {
               while (needToWait()) {
                  wait();
                  Log.d("x", "woke up from wait");
               }
            }
            if (mDone) {
               break;
            }
            changed = pSv.mSizeChanged;
            w = mWidth;
            h = mHeight;
            pSv.mSizeChanged = false;
            this.mSizeChanged = false;
         }
         if (needStart) {
            Log.d("x", "Need to start");
            mEglHelper.start(configSpec);
            tellRendererSurfaceCreated = true;
            changed = true;
         }
         if (changed) {
            Log.d("x", "Change");
            gl = (GL10) mEglHelper.createSurface(pSv.mHolder);
            tellRendererSurfaceChanged = true;
         }
         if (tellRendererSurfaceCreated) {
            Log.d("x", "Render Surface created");
            mRenderer.surfaceCreated(gl);
            tellRendererSurfaceCreated = false;
         }
         if (tellRendererSurfaceChanged) {
            Log.d("x", "Render Surface changed");
            mRenderer.sizeChanged(gl, w, h);
            tellRendererSurfaceChanged = false;
         }
         if ((w > 0) && (h > 0)) {
            Log.d("x", "Drawing frame now");
            mRenderer.drawFrame(gl);
            mEglHelper.swap();
         }
      }
      mEglHelper.finish();
   }

   private boolean needToWait() {
      return ((!mSizeChanged) || mPaused || (! mHasFocus) || (! mHasSurface)
      || mContextLost)
      && (! mDone);
   }

   public void surfaceCreated() {
      synchronized(this) {
      mHasSurface = true;
      mContextLost = false;
      notify();
      }
   }

   public void surfaceDestroyed() {
      synchronized(this) {
      mHasSurface = false;
      notify();
      }
   }

   public void onPause() {
      synchronized (this) {
      mPaused = true;
      }
   }

   public void onResume() {
      synchronized (this) {
      mPaused = false;
      notify();
      }
   }

   public void onWindowFocusChanged(boolean hasFocus) {
      synchronized (this) {
         mHasFocus = hasFocus;
         if (mHasFocus == true) {
            notify();
         }
      }
   }

   public void onWindowResize(int w, int h) {
      synchronized (this) {
         mWidth = w;
         mHeight = h;
         pSv.mSizeChanged = true;
         this.mSizeChanged = true;
         Log.d("x","window size changed. w, h:" + w + "," + h);
         if (w > 0)
         {
            notify();
         }
      }
   }

   public void requestExitAndWait()
   {
      synchronized(this) {
         mDone = true;
         notify();
      }
      try {
         join();
      } catch (InterruptedException ex) {
         Thread.currentThread().interrupt();
      }
   }
}


